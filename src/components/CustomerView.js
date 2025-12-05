
import { useEffect, useState } from "react";
import { fetchMyBalance, fetchMyTransactions, topup } from "../api";

export default function CustomerView({ username, password }) {
  const [balance, setBalance] = useState(0);
  const [transactions, setTransactions] = useState([]);
  const [cardNumber, setCardNumber] = useState("4123456789012345");
  const [pin, setPin] = useState("");
  const [amount, setAmount] = useState(10);
  const [message, setMessage] = useState("");

  const loadData = () => {
    fetchMyBalance(username, password).then(setBalance);
    fetchMyTransactions(username, password).then(setTransactions);
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleTopup = async (e) => {
    e.preventDefault();
    const resp = await topup(username, password, cardNumber, pin, amount);
    setMessage(resp.message);
    loadData();
  };

  return (
    <div>
      <h2>Customer Dashboard</h2>
      <p>Balance: <strong>{balance}</strong></p>

      <h3>Topup</h3>
      <form onSubmit={handleTopup}>
        <div>
          <label>Card Number</label>
          <input value={cardNumber} onChange={e => setCardNumber(e.target.value)} />
        </div>
        <div>
          <label>PIN</label>
          <input type="password" value={pin} onChange={e => setPin(e.target.value)} />
        </div>
        <div>
          <label>Amount</label>
          <input type="number" value={amount} onChange={e => setAmount(Number(e.target.value))} />
        </div>
        <button type="submit">Topup</button>
      </form>
      {message && <p>{message}</p>}

      <h3>My Transactions</h3>
      <table border="1" cellPadding="4">
        <thead>
          <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Success</th>
            <th>Message</th>
            <th>Created At</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map(t => (
            <tr key={t.id}>
              <td>{t.id}</td>
              <td>{t.type}</td>
              <td>{t.amount}</td>
              <td>{t.success ? "YES" : "NO"}</td>
              <td>{t.message}</td>
              <td>{t.createdAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
