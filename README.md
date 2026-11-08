# CleanShip

**Open-Source, Self-Hosted Shipping Rate Engine**

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status: WIP](https://img.shields.io/badge/Status-Work%20in%20Progress-yellow.svg)](#project-status)

**CleanShip** is an open-source shipping rate engine designed to decouple complex carrier tariff rules from e-commerce application code.

Instead of hard-coding fragile shipping calculations into your checkout backend, CleanShip provides a dedicated **management UI** to configure carrier rules visually, and a **fast evaluation API** to return eligible shipping quotes at checkout.

---

## Core Goals

* **Visual Carrier & Tariff Management:** Configure carriers, zones, and rate charts through a dedicated back-office interface without redeploying your e-commerce store.
* **Geographic & Postal Targeting:** Group countries and subdivisions into custom shipping zones using flexible postal code matching rules (`starts with`, `ends with`, `contains`, `equals`, combined with logical `AND`, `OR`, `NOT`).
* **Parcel & Order Constraints:** Filter eligible carriers automatically based on parcel weight brackets, 3D dimensions, cubic volume, and cart amount limits.
* **Transparent Price Breakdown:** Apply multi-step pricing rules—such as free shipping thresholds, flat fees, and percentage surcharges—while returning a full, auditable breakdown of how the final price was calculated.
* **Multi-Tenant by Design:** Manage shipping rules for a single store, multiple brands, or a multi-merchant platform from a single deployment.

---

## How It Fits In

1. **Configure (UI & BFF):** Logistics or e-commerce teams define carriers, geographic zones, constraints, and pricing rules in the CleanShip interface.
2. **Evaluate (Checkout API):** When a customer reaches checkout, your backend sends the destination, parcel details, and cart total to CleanShip.
3. **Display Quotes:** CleanShip returns all eligible carriers along with their base price, applied discounts/surcharges, and final price.

---

## Project Status

🚧 **CleanShip is currently under active development (early stage).**  
The domain model and core rating engine are being built first, followed by the API, BFF, and management interface.

---

## License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

Copyright (c) 2026 **Anthony DUSART**
