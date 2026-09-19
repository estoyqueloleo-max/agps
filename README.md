# AGPS-TrackerOm (A-GPS Tracker++)

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0%20%2F%20Community%20Preservation-blue.svg)](LICENSE)
[![Status: Maintained](https://img.shields.io/badge/Status-Community%20Revival-brightgreen.svg)]()
[![In Memory of](https://img.shields.io/badge/In%20Memory%20of-Giorgio%20Battistoni-purple.svg)](#️-in-memory-of-giorgio-battistoni-giobat)

> **A community revival and preservation project dedicated to keeping the beloved outdoor and trekking navigation app alive, free, ad-free, and accessible to everyone.**

---

## 📖 The Story

### 🕊️ In Memory of Giorgio Battistoni ("giobat")

The original creator and sole visionary behind **A-GPS Tracker** and **AGPS-TrackerOm** was Italian developer and trekking enthusiast **Giorgio Battistoni** (`giobat`). 

Giorgio crafted an application that stood out in a crowded market of outdoor navigation apps. Rather than relying on simple phone sensors or cloud subscriptions, he built a powerhouse tool for mountaineers and hikers: integrating **NASA Digital Elevation Models (DEM)**, **EGM96 geoid calculation**, **Mapsforge offline vector mapping**, and resilient background GPS tracking that never drained the battery or stopped recording when the screen turned off.

Tragically, in **May 2023**, Giorgio passed away after a battle with an aggressive cancer.

Following his passing, Giorgio's family reached out to the trekking community and specialized portals such as [HikerApps.com](https://hikerapps.com/) (documented in their comprehensive review [HikerApps: AGPS-TrackerOm Review](https://hikerapps.com/blog/agps-trackerom)):

> *"We have to inform you that unfortunately the creator and main developer of A-GPS Tracker, Giorgio Battistoni (gio bat) passed away in May (2023) due to a lightning-fast cancer. We, his family, decided to honor his memory keeping the app alive and we are working hard to recover everything..."*
> 
> — *Family statement shared with [HikerApps](https://hikerapps.com/blog/agps-trackerom)*

---

### 🛑 Why the App Vanished from Google Play

Despite his family's initial hopes and determination, keeping legacy mobile applications on commercial app stores has become nearly impossible for surviving relatives and independent developers:

1. **Escalating Google Target API Policies:** Google Play mandates regular updates to the latest Android `targetSdkVersion`. Legacy apps that fall behind the annual cutoff are hidden and ultimately delisted from search results.
2. **Aggressive Developer Identity Verification:** In 2023–2024, Google enforced rigorous personal and organizational identity mandates, requiring D-U-N-S numbers for entities and biometric/government ID validation for personal accounts. Without the original developer present, heir accounts often encounter insurmountable bureaucratic dead ends.
3. **Infrastructure Expiration:** Over time, legacy backend services and domains expired, leaving the original distribution channels broken.

By early **2025**, both **A-GPS Tracker** and **AGPS-TrackerOm** had disappeared entirely from the Google Play Store, leaving thousands of hikers without updates or an easy way to install the app on newer smartphones.

---

## 🎯 The Mission of this Repository

The goal of this project is straightforward: **to ensure Giorgio's work does not fade into digital oblivion.**

- **Preserve and Modernize:** Decompile, clean up, and rebuild the application from the latest preserved package (`v1.8`).
- **Completely Ad-Free & Unlocked:** All dead AdMob libraries (3,000+ obsolete classes) and expired Google Play In-App Billing flows have been completely removed. The app runs in fully unlocked, permanent ad-free mode.
- **Keep Core Offline Features Intact:** Modern **Mapsforge 0.25.0** vector rendering with native multi-threading and parent tile rendering (replacing obsolete 2016 bytecode with official upstream libraries and Android 9–14 fixes), NASA DEM altitude interpolation, GPX track logs, offline contour styling, and battery-friendly foreground tracking remain 100% operational.
- **Stable Revival Baseline:** This main repository is dedicated to keeping the core app rock-solid, bug-free, and faithfully preserved. Experimental features or major paradigm shifts are designated for separate evolution forks to protect the stability and lightweight footprint of this baseline.

---

## 🎗️ In His Honor: Supporting Cancer Foundations

Instead of monetizing this application, collecting donations for maintenance, or showing advertisements, this project is dedicated to the fight against cancer in memory of Giorgio.

We invite users who enjoy and benefit from this application to honor Giorgio by considering a donation directly to recognized organizations dedicated to cancer research, patient support, and prevention:

| Organization | Description | Link |
| :--- | :--- | :--- |
| **Movember** | The leading global charity focused on men's health, raising awareness and funding research for prostate cancer, testicular cancer, and mental health. | [movember.com](https://movember.com/) |
| **Fundación UAPO** *(Spain)* | *Unidad de Apoyo a Pacientes Oncológicos* — A pioneering non-profit foundation providing integrated, free physical exercise, oncology nutrition, and psychological support to improve the quality of life of cancer patients. | [fundacionuapo.org](https://fundacionuapo.org/) |

> *Note: This project is completely independent and has no financial ties or affiliate relationships with these organizations. 100% of any contribution goes directly to the causes through their official channels.*

---

## ✨ Key Features

- 🛰️ **NASA WorldWind DEM & EGM96 Geoid Modeling:** Unlike standard GPS tracking which can have elevation errors of ±30 meters, AGPS-TrackerOm uses NASA elevation models and geoid calculations to deliver realistic altitude and elevation profiles.
- 🗺️ **Offline Vector Maps:** Native support for OpenStreetMap / OpenAndroMaps via Mapsforge (`.map` files) with detailed hiking themes (*Elevate* and *Elements*), contour lines, and hillshading.
- 🔋 **Resilient Background Service:** Optimized foreground GPS service with explicit battery optimization handling so you never lose a track mid-mountain.
- 📍 **GPX Management & Waypoints:** Record, import, export, and analyze `.gpx` tracks. Attach geo-tagged photos and notes to waypoints.
- 🔒 **100% Private & Local:** No user tracking, no telemetry, no analytics, no external servers required. Your data remains strictly on your device.

---

## 📥 Download & Installation

### Option A: Direct APK Download (Recommended)

1. Go to the [Releases](https://github.com/estoyqueloleo-max/agps/releases) tab of this repository.
2. Download the latest `AGPS-Tracker-Community.apk` (automatically built and attached on each GitHub Release).
3. On your Android device, open the downloaded file.
4. If prompted, grant permission to *Install unknown apps* for your browser or file manager.
5. Launch **A-GPS Tracker++ (Community)** and start trekking!

---

## 🗺️ Offline Maps & Elevation Data (Where to Get Maps)

A-GPS Tracker++ is engineered to operate completely offline in remote wilderness areas without cellular coverage. It utilizes lightweight, high-performance **Mapsforge offline vector maps (`.map`)** along with Tobias Kühn's acclaimed **Elevate** outdoor theme and **NASA Digital Elevation Models (DEM)**.

### 1. In-App Map Downloader (Legacy Cloud Archive)
The original application included a built-in regional map catalog backed by a private cloud server maintained by the developer.

> [!NOTE]
> **Privacy & Infrastructure Safeguard:** To avoid overwhelming legacy infrastructure and out of respect for the developer's family, all specific server hostnames, IP addresses, and access credentials have been anonymized and decoupled from the public code repository.

* **Storage Footprint & Preservation Archives:** The complete map and elevation archive comprises:
  - **445 regional `.map` files**, totaling **70.91 GB**.
    - 🏛️ Preserved on Internet Archive: [agps-tracker-mapsforge-offline-maps](https://archive.org/details/agps-tracker-mapsforge-offline-maps)
  - **14,295 NASA SRTM high-resolution DEM tiles**, totaling **97.5 GB** (1-arc-second / 30m resolution elevation data).
    - 🏛️ Preserved on Internet Archive: [agps-tracker-nasa-srtm-dem](https://archive.org/details/agps-tracker-nasa-srtm-dem)
  - **Total preserved dataset:** **~168.4 GB** across 14,740 files, now hosted permanently at zero cost.
* **VPS Maintenance Cost Benchmark (Why Community Preservation Matters):**
  Hosting ~170 GB of continuous high-bandwidth geospatial assets on a private VPS (e.g. [Contabo Cloud VPS](https://contabo.com/en/vps/)) requires ~5.50 € – 6.50 € / month (~78 € / year) plus domain registration (~15 € – 20 € / year), totaling roughly **~95 € – 100 € per year**.
  Over 40 months since Giorgio's passing in [May 2023](https://hikerapps.com/blog/agps-trackerom), maintaining private infrastructure would have accumulated **over ~320 € – 350 €** in recurring fees.
* **Zero-Cost Community Independence:**
  Expecting a deceased developer's family to fund a cloud server indefinitely was unsustainable. By migrating the full dataset to permanent, zero-cost public storage at the **Internet Archive (Archive.org)** and supporting direct installation from **OpenAndroMaps**, the application is now fully independent, self-sustaining, and guaranteed to survive indefinitely.
* **Usage:** Open the app menu and select **Download Map** (or tap the map icon on the action bar), choose your region (e.g. *Europe* → *Spain* → *Comunidad de Madrid*), and the app will download, decompress, and activate the map.

### 2. Direct 1-Click Install via OpenAndroMaps
The app natively supports the `mf-v4-map://` intent scheme for seamless map installation:
1. Open your mobile browser and visit [OpenAndroMaps Downloads](https://www.openandromaps.org/en/downloads).
2. Find your desired country or hiking area (e.g., Alps, Pyrenees, Scandinavia, Patagonia).
3. Tap the **Install to Android** button.
4. Select **A-GPS Tracker++ (Community)** as the target application.
5. The download will start automatically in the background and the map will be loaded upon completion.

### 3. Manual Installation (Custom Mapsforge `.map` Files)
You can use any standard Mapsforge-compatible vector map file (downloaded from [OpenAndroMaps](https://www.openandromaps.org/), [Freizeitkarte-OSM](https://www.freizeitkarte-osm.de/), or generated via Osmosis/Mapsforge map writer):
* Copy your `.map` files to the app's dedicated storage directory:
  ```
  Android/data/com.giobat.AgpsTrackerPP.community/files/Maps/
  ```
* Launch the app — it will automatically discover and load the map tiles.

### 4. NASA DEM (Digital Elevation Models)
To display accurate, drift-free elevation contours and terrain relief without relying on noisy satellite vertical signals:
* DEM tiles (`.hgt` or DEM format) are automatically fetched as needed or can be manually placed into:
  ```
  Android/data/com.giobat.AgpsTrackerPP.community/files/dems/
  ```

---

## 🛠️ Building from Source

### Requirements
- **JDK:** OpenJDK 17 or 21
- **Android SDK:** Platform API 34, Build-Tools 34.0.0
- **Gradle:** Wrapper included (`v8.4`)

### Build & Test Steps
```bash
# Clone the repository
git clone https://github.com/estoyqueloleo-max/agps.git
cd agps

# Run automated tests (Unit & Robolectric component tests)
./gradlew testDebugUnitTest

# Build the Release APK using the automated script
# (Runs tests, cleans, builds release, and copies APK to ./dist/)
./build_release.sh

# Or build manually with Gradle:
./gradlew assembleRelease
# Output location: app/build/outputs/apk/release/app-release.apk

# Build the Debug APK:
./gradlew assembleDebug
```

---

## 📜 Acknowledgements & Attribution

- **Original Developer:** [Giorgio Battistoni](mailto:giobat.mailbox@gmail.com) ("giobat") — Heartfelt gratitude for creating an application that has guided so many safely through trails and peaks.
- **HikerApps Community Portal:** [HikerApps.com](https://hikerapps.com/) — Special recognition for their in-depth review and tribute ([AGPS-TrackerOm Article](https://hikerapps.com/blog/agps-trackerom)), helping preserve Giorgio's memory and connecting the hiking community ([Contact HikerApps](https://hikerapps.com/contact)).
- **Mapsforge:** Offline vector map engine for Android.
- **OpenAndroMaps & OpenStreetMap:** High-quality hiking and topographical map data.
- **NASA WorldWind:** Digital Elevation Models and geoid reference data.

## ⚖️ License

This community preservation project is distributed under the **Apache License, Version 2.0**, honoring the original licensing established by Giorgio Battistoni for AGPS-Tracker-Om and allowing the community to freely maintain, adapt, and keep the application alive. See the full [LICENSE](LICENSE) file for terms and conditions.

---

*“To walk in nature is to witness a thousand miracles.” — In loving memory of Giorgio Battistoni.*
