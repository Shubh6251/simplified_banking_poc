

const BASE_URL = "http://localhost:8082";

export function authHeader(username, password) {
  return "Basic " + btoa(`${username}:${password}`);
}

export async function fetchAllTransactions(username, password) {
  const res = await fetch(`${BASE_URL}/admin/transactions`, {
    headers: {
      Authorization: authHeader(username, password),
    },
  });
  return res.json();
}

export async function fetchMyBalance(username, password) {
  const res = await fetch(`${BASE_URL}/customer/me/balance`, {
    headers: {
      Authorization: authHeader(username, password),
    },
  });
  return res.json();
}

export async function fetchMyTransactions(username, password) {
  const res = await fetch(`${BASE_URL}/customer/me/transactions`, {
    headers: {
      Authorization: authHeader(username, password),
    },
  });
  return res.json();
}

export async function topup(username, password, cardNumber, pin, amount) {
  const res = await fetch(`${BASE_URL}/customer/me/topup`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: authHeader(username, password),
    },
    body: JSON.stringify({ cardNumber, pin, amount }),
  });
  return res.json();
}
