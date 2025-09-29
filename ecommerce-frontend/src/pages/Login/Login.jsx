import { useState, useContext } from "react";
import { login as apiLogin } from "../../api/auth.service";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";

export default function Login() {
  const [usernameInput, setUsernameInput] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await apiLogin(usernameInput, password);
      login(data.accessToken, data.username);
      navigate("/");
    } catch (err) {
      alert("Login error: " + (err.response?.data?.message || err.message));
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "100vw",
        height: "100vh",
        backgroundColor: "inherit",
      }}
    >
      <form
        onSubmit={handleSubmit}
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "15px",
          width: "400px",
          padding: "40px",
          borderRadius: "12px",
          boxShadow: "0 4px 15px rgba(0,0,0,0.2)",
          backgroundColor: "inherit",
        }}
      >
        <h2 style={{ textAlign: "center", color: "white" }}>Login</h2>
        <input
          placeholder="Username"
          value={usernameInput}
          onChange={(e) => setUsernameInput(e.target.value)}
          style={{
            padding: "12px",
            fontSize: "16px",
            borderRadius: "6px",
            border: "1px solid #ccc",
            backgroundColor: "rgba(255,255,255,0.1)",
          }}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{
            padding: "12px",
            fontSize: "16px",
            borderRadius: "6px",
            border: "1px solid #ccc",
            backgroundColor: "rgba(255,255,255,0.1)",
          }}
        />
        <button
          type="submit"
          style={{
            padding: "14px",
            fontSize: "16px",
            borderRadius: "6px",
            backgroundColor: "#00aaff",
            color: "white",
            border: "none",
            cursor: "pointer",
            transition: "background-color 0.3s",
          }}
          onMouseEnter={(e) => (e.target.style.backgroundColor = "#0088cc")}
          onMouseLeave={(e) => (e.target.style.backgroundColor = "#00aaff")}
        >
          Login
        </button>
      </form>
    </div>
  );
}