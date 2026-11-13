# 🏍️ Outdoor TRON Lightcycle Challenge & P2P Mesh Guide

> **Transforming outdoor running and cycling into an in-person tactical arcade game using real-world GPS and serverless P2P communication.**

---

## 📖 Introduction & Concept

The **Outdoor TRON Lightcycle Challenge** is a creative gamification mode developed for **A-GPS Tracker++**. Players running or cycling in parks, open esplanades, or trails leave a persistent, glowing neon "light wall" behind them calculated directly from high-precision GPS telemetry.

* **Cyan Light Wall (`#00F0FF`):** Your own light trail.
* **Orange Light Wall (`#FF7700`):** The opponent's light trail.
* **Magenta Perimeter Ring (`#FF0055`):** The arena boundary.
* **Objective:** Maneuver through the physical terrain to force your opponent to cross a light wall or exit the arena, while avoiding crossing any trail yourself.
* **Collision Event (*DEREZZED*):** Crossing an existing light wall (yours or your opponent's) or leaving the arena perimeter immediately causes a crash.

---

## 🧩 Pluggable Architecture ("Zero Pollution" Rule)

To faithfully honor Giorgio Battistoni's legacy and protect the rock-solid stability of the baseline hiking app, the TRON challenge was implemented using a **strictly pluggable architecture**:

```
app/src/
├── main/             # Shared core, Mapsforge maps, GPX, altitude models
│   └── java/.../plugin/
│       ├── GameExtension.java           # Interface contract
│       └── GameExtensionProvider.java   # Dynamic factory
│
├── community/        # Standard Trekking Edition (com.giobat.AgpsTrackerPP.community)
│   └── java/.../plugin/
│       └── GameExtensionImpl.java       # No-Op stub (0 bytes overhead)
│
└── tron/             # Outdoor TRON Edition (com.giobat.AgpsTrackerPP.community.tron)
    ├── java/.../tron/
    │   ├── TronCollisionEngine.java     # Local 2D planar math & ray crossing
    │   ├── TronOverlayManager.java      # Glowing neon Mapsforge layers
    │   ├── TronFeedbackManager.java     # ToneGenerator acoustic & haptic cues
    │   ├── PingoBridgeManager.java      # Scoped IPC sidecar to Pingo P2P
    │   └── TronArenaActivity.java       # Fullscreen tactical HUD & Map
    └── res/layout/activity_tron_arena.xml
```

1. **Community Edition:** Standard build has **zero Tron code**, zero extra permissions, and zero performance impact.
2. **Tron Edition:** Compiled with its own package ID and application title (`A-GPS Tracker Tron`), allowing both editions to coexist side-by-side on the same phone.

---

## 🎮 Game Modes

### 1. 🏃 Solitary Survival Mode (Offline / Single Player)
* **No network or server required.**
* Click **START** to drop into the arena centered on your current location (default 250 m radius).
* As you run or cycle, your trail is plotted on the map.
* **Immunity Buffer:** The latest 8 meters of your trail are immune to avoid false self-collisions during sharp turns.
* **Shrinking Arena (Ring: ON):** Toggle the dynamic perimeter ring to simulate a Battle Royale scenario contracting at 1 meter/second towards a minimum 30-meter circle.
* **Acoustic & Haptic Alerts:** Android `ToneGenerator` and `Vibrator` issue warning beeps when you are within 1.2 meters of a wall.

### 2. 👥 Multiplayer Mode via PINGO Sidecar (P2P Mesh)
* Works in tandem with **PINGO** (`com.pingo.app`), which acts as an offline/online P2P signaling and WebRTC DataChannel engine.
* **Strict Retransmission Scoping ("A quién se retransmite"):**
  * Game telemetry (1 Hz coordinates, derezzed signals, arena sync) is **strictly isolated** to the active match (`matchId`) and designated opponent (`targetPeerId`).
  * Telemetry is **NEVER** broadcast to personal contacts or family in your Pingo agenda.
  * If no match participants are connected, packets are safely dropped rather than leaking to general contacts.
* **Real-time Opponent Tracking:** Opponent positions appear instantly as a glowing orange neon light wall.
* **Victory Condition:** When the opponent collides with any trail or leaves the arena, a derezzed signal is received and the HUD triggers an immediate **VICTORY** fanfare.

---

## 🚀 How to Set Up a Multiplayer Match

1. **Install Applications:**
   * Install `AGPS-Tracker-Tron.apk` on both devices.
   * Install `Pingo.apk` on both devices.
2. **Pair with Opponent in Pingo:**
   * Open Pingo, exchange Peer IDs (via QR code or contact agenda), and ensure the P2P connection is active.
   * Start the Pingo background service so it continues relaying packets in the background.
3. **Configure the Match in A-GPS Tracker:**
   * Open **A-GPS Tracker Tron** and tap **TRON CHALLENGE** from the options menu.
   * Tap **LOBBY**:
     * Set **Match ID** (e.g. `PARK-MATCH-1`). Both players must enter the exact same Match ID.
     * *(Optional)* Set **Opponent Peer ID** to lock communication strictly to that person.
   * Tap **START**:
     * The arena boundary is automatically synchronized.
     * Both riders/runners start their engines and the match begins!

---

## ⚠️ Outdoor Safety Guidelines

> [!CAUTION]
> Always prioritize physical safety over game tactics:
> * Play exclusively in open, pedestrian-only areas: public parks, sports complexes, school tracks, or closed fields.
> * **NEVER** play on public roads, bicycle lanes with motor traffic, or near cliffs/hazards.
> * Use audio and haptic feedback rather than staring continuously at the phone screen while in motion.
> * Maintain awareness of your surroundings and other pedestrians at all times.

---

## 🛠️ Build & Development Commands

### Automated Build Pipeline
```bash
# Builds both release APKs and places them in ./dist/
./build_release.sh
```

### Running Automated Test Suites
```bash
# Test pure Community edition (baseline revival)
./gradlew testCommunityDebugUnitTest

# Test Tron edition (101 automated unit & Robolectric visual tests)
./gradlew testTronDebugUnitTest
```
