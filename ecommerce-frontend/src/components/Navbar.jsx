import { Link } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

export default function Navbar() {
  const { username, logout } = useContext(AuthContext);

  return (
    <nav style={{ padding: "10px", borderBottom: "1px solid #ccc", marginBottom: "20px" }}>
      <Link to="/products" style={{ marginRight: "10px" }}>Products</Link>
      {username && (
        <>
          <Link to="/orders" style={{ marginRight: "10px" }}>Orders</Link>
          <Link to="/customers" style={{ marginRight: "10px" }}>Customers</Link>
          <span style={{ marginRight: "10px" }}>Hello, {username}</span>
          <button onClick={logout}>Logout</button>
        </>
      )}
      {!username && <Link to="/login">Login</Link>}
    </nav>
  );
}