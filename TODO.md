# 🗺️ A-GPS Tracker++ (Community) — Roadmap & Active Tracking

This document tracks future evolution ideas and references all active engineering milestones managed via **GitHub Issues**.

---

## 📌 Active Milestones & GitHub Issues

All technical development, bug fixes, and core refactoring are tracked as GitHub Issues:

| Issue | Title | Type | Status |
|---|---|---|---|
| [#1](https://github.com/estoyqueloleo-max/agps/issues/1) | Review drawing of DEMS | Investigation | Open |
| [#2](https://github.com/estoyqueloleo-max/agps/issues/2) | Recover website and documentation (multi-language & contrast) | Documentation | PR [#11](https://github.com/estoyqueloleo-max/agps/pull/11) |
| [#4](https://github.com/estoyqueloleo-max/agps/issues/4) | Think about an automatic updater for libraries | Enhancement | Open |
| [#5](https://github.com/estoyqueloleo-max/agps/issues/5) | Fix double-tap to import route in file browser | Bug | Open |
| [#6](https://github.com/estoyqueloleo-max/agps/issues/6) | Elevation profile downsampling for long routes (>10,000 pts) | Enhancement | Open |
| [#7](https://github.com/estoyqueloleo-max/agps/issues/7) | Selective core deobfuscation: `IntServLocGpsPP` and `MainActivity` | Architecture | Open |
| [#8](https://github.com/estoyqueloleo-max/agps/issues/8) | Improve map viewport loading and dynamic zoom level clamping | Performance | Open |
| [#9](https://github.com/estoyqueloleo-max/agps/issues/9) | Evaluate public DEM mirrors (NASA Earthdata, USGS, Viewfinder) | Infrastructure | Open |
| [#10](https://github.com/estoyqueloleo-max/agps/issues/10) | i18n audit: standardize English base strings and complete translations | i18n | Open |
| [#12](https://github.com/estoyqueloleo-max/agps/issues/12) | Modernize Android lifecycle and background location permission flow (Android 14+) | Architecture | Open |
| [#13](https://github.com/estoyqueloleo-max/agps/issues/13) | Expand automated test coverage: GPX/DEM unit tests, service lifecycle, and Espresso UI smoke suite | QA / Testing | Open |

---

## 💡 Part 2: Creative Concepts (Future Evolution Forks)

> [!NOTE]
> To keep this main repository as the **rock-solid, stable community revival**, experimental concepts (Tron, Mazes, P2P gaming) will be developed in dedicated forks.

### 1. ✍️ GPS Route Art / "Draw with Your Steps"
- **Concept:** Ghost guide overlay for tracing shapes, drawings, and text onto real-world geography using your footsteps.
- **Features:** Vector stroke smoothing, art export (SVG / GPX / PNG), message reveal mode for friends.

### 2. 🏍️ Outdoor Tron Lightcycle Challenge
- **Concept:** Competitive outdoor game where players leave persistent colored "light walls" on the map based on GPS coordinates.
- **Features:** Real-time collision detection (crossing an opponent's trail triggers elimination), dynamic shrinking boundary, audio/haptic cues.

### 3. 🧩 Outdoor GPS Labyrinths & Time Attack
- **Concept:** Procedural virtual labyrinths projected onto outdoor locations (parks, plazas, trails).
- **Features:** Virtual impassable walls, Fog of War exploration mode, time attack leaderboards, QR code maze sharing.

### 4. 🌐 Decentralized P2P Networking (P2PT)
- **Concept:** Serverless peer-to-peer position exchange for multiplayer modes without relying on central game servers.
- **Features:** WebTorrent trackers + WebRTC data channels, zero server infrastructure, offline Bluetooth Low Energy (BLE) fallback.

---

## 🗺️ Reference: Map Compilation & Catalog Tools

Giorgio compiled regional `.map` files from OpenStreetMap data using Mapsforge and indexed them via `creaLists.jar`.

#### Compilation Pipeline:
1. Regional `.osm.pbf` extracts from [Geofabrik](https://download.geofabrik.de/).
2. 10m/20m contour generation with [`phyghtmap`](http://katze.tfiu.de/projects/phyghtmap/) from NASA SRTM / Copernicus DEM.
3. Binary compilation to Mapsforge `.map`:
   ```bash
   osmosis \
     --read-pbf file="region-with-contours.osm.pbf" \
     --mapfile-writer file="region.map" \
     type=hd tag-conf-file=tag-mapping.xml \
     bbox=minLat,minLon,maxLat,maxLon
   ```

#### Index Header Reader (`build_catalog.py`):
Each `.map` file stores its bounding box at byte offset 44 (4× 32-bit big-endian integers in microdegrees):
```python
import struct, os

def index_maps(root_dir, output_file):
    with open(output_file, 'w') as out:
        for dirpath, _, filenames in os.walk(root_dir):
            for f in filenames:
                if f.endswith('.map'):
                    full_path = os.path.join(dirpath, f)
                    rel_path = "./" + os.path.relpath(full_path, root_dir)
                    size = os.path.getsize(full_path)
                    with open(full_path, 'rb') as raf:
                        raf.seek(44)
                        minlat, minlon, maxlat, maxlon = struct.unpack('>iiii', raf.read(16))
                        out.write(f"{rel_path},{minlat/1e6},{minlon/1e6},{maxlat/1e6},{maxlon/1e6},{size}\n")
```

#### Elevation DEM Sources:
- USGS EarthExplorer / NASA Earthdata: https://earthexplorer.usgs.gov/
- Viewfinder Panoramas (Jonathan de Ferranti): http://viewfinderpanoramas.org/
- Derek Watkins' SRTM Tile Grabber: https://dwtkns.com/srtm30m/
