#!/usr/bin/env bash
set -euo pipefail

# ==============================================================================
# Release build script for A-GPS Tracker++ Community
# ==============================================================================

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

# Terminal colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${BLUE}======================================================${NC}"
echo -e "${BLUE}  🚀 Building A-GPS Tracker++ Community (RELEASE)     ${NC}"
echo -e "${BLUE}======================================================${NC}"

SKIP_TESTS=false

# Parse arguments
for arg in "$@"; do
    case $arg in
        --skip-tests)
            SKIP_TESTS=true
            shift
            ;;
        -h|--help)
            echo "Usage: ./build_release.sh [OPTIONS]"
            echo ""
            echo "Options:"
            echo "  --skip-tests    Skip unit tests before building"
            echo "  -h, --help      Show this help message"
            exit 0
            ;;
    esac
done

# 1. Ensure gradlew is executable
if [ ! -x "./gradlew" ]; then
    echo -e "${YELLOW}Setting execute permission on ./gradlew...${NC}"
    chmod +x ./gradlew
fi

# 2. Decrypt secrets with SOPS if available and encrypted file exists
if [ ! -f "secrets.properties" ] && [ -f "secrets.enc.properties" ]; then
    echo -e "${YELLOW}Decrypting secrets.enc.properties with SOPS...${NC}"
    if command -v sops >/dev/null 2>&1; then
        sops -d --input-type dotenv --output-type dotenv secrets.enc.properties > secrets.properties || true
    fi
fi

# 3. Run unit tests (unless --skip-tests is set)
if [ "$SKIP_TESTS" = false ]; then
    echo -e "\n${BLUE}🧪 [1/3] Running unit and component tests...${NC}"
    ./gradlew testCommunityDebugUnitTest testTronDebugUnitTest
    echo -e "${GREEN}✓ All tests passed.${NC}"
else
    echo -e "\n${YELLOW}⚠️  Skipping unit tests (--skip-tests flag active)${NC}"
fi

# 4. Clean and build the Release APK
echo -e "\n${BLUE}📦 [2/3] Building Release APKs with Gradle...${NC}"
./gradlew clean assembleRelease

# 5. Organize and rename the output APKs
echo -e "\n${BLUE}📂 [3/3] Preparing final APKs...${NC}"

RELEASE_DIR="$PROJECT_DIR/dist"
mkdir -p "$RELEASE_DIR"

VERSION_NAME=$(git describe --tags --always 2>/dev/null | sed 's/^v//' || echo "0.2.0")
VERSION_CODE=$(git rev-list --count HEAD 2>/dev/null | awk '{print $1 + 100}' || echo "101")

# Community Edition
COMMUNITY_APK="$PROJECT_DIR/app/build/outputs/apk/community/release/app-community-release.apk"
if [ -f "$COMMUNITY_APK" ]; then
    TARGET_COMMUNITY_VERSION="$RELEASE_DIR/AGPS-Tracker-Community-v${VERSION_NAME}-b${VERSION_CODE}.apk"
    TARGET_COMMUNITY_LATEST="$RELEASE_DIR/AGPS-Tracker-Community-latest.apk"
    cp "$COMMUNITY_APK" "$TARGET_COMMUNITY_VERSION"
    cp "$COMMUNITY_APK" "$TARGET_COMMUNITY_LATEST"
    echo -e "✓ Community APK:  ${YELLOW}$TARGET_COMMUNITY_VERSION${NC} ($(du -h "$TARGET_COMMUNITY_VERSION" | cut -f1))"
else
    echo -e "${RED}❌ Warning: Community APK not found at $COMMUNITY_APK${NC}"
fi

# Tron Edition
TRON_APK="$PROJECT_DIR/app/build/outputs/apk/tron/release/app-tron-release.apk"
if [ -f "$TRON_APK" ]; then
    TARGET_TRON_VERSION="$RELEASE_DIR/AGPS-Tracker-Tron-v${VERSION_NAME}-b${VERSION_CODE}.apk"
    TARGET_TRON_LATEST="$RELEASE_DIR/AGPS-Tracker-Tron-latest.apk"
    cp "$TRON_APK" "$TARGET_TRON_VERSION"
    cp "$TRON_APK" "$TARGET_TRON_LATEST"
    echo -e "✓ Tron APK:       ${YELLOW}$TARGET_TRON_VERSION${NC} ($(du -h "$TARGET_TRON_VERSION" | cut -f1))"
else
    echo -e "${YELLOW}⚠️ Tron APK not found at $TRON_APK${NC}"
fi

echo -e "\n${GREEN}======================================================${NC}"
echo -e "${GREEN}  🎉 Release builds completed successfully!            ${NC}"
echo -e "${GREEN}======================================================${NC}"
if [ -f "${TARGET_COMMUNITY_VERSION:-}" ]; then
    echo -e "📍 Community APK: ${YELLOW}$TARGET_COMMUNITY_VERSION${NC} ($(du -h "$TARGET_COMMUNITY_VERSION" | cut -f1))"
    echo -e "   Latest link:   ${YELLOW}$TARGET_COMMUNITY_LATEST${NC}"
fi
if [ -f "${TARGET_TRON_VERSION:-}" ]; then
    echo -e "📍 Tron APK:      ${YELLOW}$TARGET_TRON_VERSION${NC} ($(du -h "$TARGET_TRON_VERSION" | cut -f1))"
    echo -e "   Latest link:   ${YELLOW}$TARGET_TRON_LATEST${NC}"
fi
echo ""
echo -e "${BLUE}💡 To install on a connected USB device:${NC}"
echo -e "   adb install -r \"$TARGET_COMMUNITY_LATEST\""
echo -e "   adb install -r \"$TARGET_TRON_LATEST\""
echo ""
