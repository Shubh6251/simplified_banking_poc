
# Simplified Banking POC

This repository contains a **simplified banking system** POC with:

- **Two backend systems (Spring Boot + H2 + Spring Security)**  
- **One frontend (React)** with a **role-based UI** (Super Admin / Customer)

The project demonstrates:

- Transaction processing (withdrawals & topups)
- Card validation & routing
- Core banking operations (balance checks & updates)
- PIN and card security using **SHA-256 hashing**
- Role-based monitoring of transactions

---

## 1. Repository Structure

```text
simplified_banking_poc/
├─ backend/
│  ├─ system1-gateway/          # System 1: Transaction Gateway
│  └─ system2-banking-core/     # System 2: Core Banking
│
└─ frontend/                    # React UI (Super Admin & Customer)
