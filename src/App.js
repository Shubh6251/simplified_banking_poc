
import { useState } from "react";
import SuperAdminView from "./components/SuperAdminView";
import CustomerView from "./components/CustomerView";

function App() {
  const [username, setUsername] = useState("superadmin");
  const [password, setPassword] = useState("password");
  const [role, setRole] = useState("SUPER_ADMIN");

  const handleRoleChange = (e) => {
    const r = e.target.value;
    setRole(r);
    if (r === "SUPER_ADMIN") {
      setUsername("superadmin");
      setPassword("password");
    } else {
      setUsername("customer1");
      setPassword("password");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h1>Simplified Banking POC</h1>
      <div>
        <label>Role: </label>
        <select value={role} onChange={handleRoleChange}>
          <option value="SUPER_ADMIN">Super Admin</option>
          <option value="CUSTOMER">Customer</option>
        </select>
      </div>

      <hr />

      {role === "SUPER_ADMIN" && (
        <SuperAdminView username={username} password={password} />
      )}
      {role === "CUSTOMER" && (
        <CustomerView username={username} password={password} />
      )}
    </div>
  );
}

export default App;
