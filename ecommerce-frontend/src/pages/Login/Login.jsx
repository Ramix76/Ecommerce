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
      navigate("/products");
    } catch (err) {
      alert("Login error: " + (err.response?.data?.message || err.message));
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "10px",
        width: "250px",
        margin: "50px auto"
      }}
    >
      <input
        placeholder="Username"
        value={usernameInput}
        onChange={e => setUsernameInput(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <button type="submit">Login</button>
    </form>
  );
}