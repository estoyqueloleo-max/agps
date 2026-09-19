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
    ./gradlew testDebugUnitTest
    echo -e "${GREEN}✓ All tests passed.${NC}"
else
    echo -e "\n${YELLOW}⚠️  Skipping unit tests (--skip-tests flag active)${NC}"
fi

# 4. Clean and build the Release APK
echo -e "\n${BLUE}📦 [2/3] Building Release APK with Gradle...${NC}"
./gradlew clean assembleRelease

# 5. Organize and rename the output APK
echo -e "\n${BLUE}📂 [3/3] Preparing final APK...${NC}"

RELEASE_DIR="$PROJECT_DIR/dist"
mkdir -p "$RELEASE_DIR"

SOURCE_APK="$PROJECT_DIR/app/build/outputs/apk/release/app-release.apk"

if [ ! -f "$SOURCE_APK" ]; then
    echo -e "${RED}❌ Error: APK not found at $SOURCE_APK${NC}"
    exit 1
fi

VERSION_NAME=$(grep "versionName" app/build.gradle | head -n1 | sed -E 's/.*"([^"]+)".*/\1/')
VERSION_CODE=$(grep "versionCode" app/build.gradle | head -n1 | tr -dc '0-9')

TARGET_APK_VERSION="$RELEASE_DIR/AGPS-Tracker-Community-v${VERSION_NAME}-b${VERSION_CODE}.apk"
TARGET_APK_LATEST="$RELEASE_DIR/AGPS-Tracker-Community-latest.apk"

cp "$SOURCE_APK" "$TARGET_APK_VERSION"
cp "$SOURCE_APK" "$TARGET_APK_LATEST"

APK_SIZE=$(du -h "$TARGET_APK_VERSION" | cut -f1)
SHA256=$(sha256sum "$TARGET_APK_VERSION" | cut -d' ' -f1)

echo -e "\n${GREEN}======================================================${NC}"
echo -e "${GREEN}  🎉 Release build completed successfully!             ${NC}"
echo -e "${GREEN}======================================================${NC}"
echo -e "📍 Output file:   ${YELLOW}$TARGET_APK_VERSION${NC}"
echo -e "📍 Latest link:   ${YELLOW}$TARGET_APK_LATEST${NC}"
echo -e "⚖️  Size:          $APK_SIZE"
echo -e "🔒 SHA256:        $SHA256"
echo ""
echo -e "${BLUE}💡 To install on a connected USB device:${NC}"
echo -e "   adb install -r \"$TARGET_APK_LATEST\""
echo ""
