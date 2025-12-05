
import { useEffect, useState } from "react";
import { fetchAllTransactions } from "../api";

export default function SuperAdminView({ username, password }) {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    fetchAllTransactions(username, password).then(setTransactions);
  }, [username, password]);

  return (
    <div>
      <h2>Super Admin – All Transactions</h2>
      <table border="1" cellPadding="4">
        <thead>
          <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Success</th>
            <th>Message</th>
            <th>Initiated By</th>
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
              <td>{t.initiatedBy}</td>
              <td>{t.createdAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
