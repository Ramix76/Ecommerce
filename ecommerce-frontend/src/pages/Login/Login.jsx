// import { useState, useContext } from "react";
// import { login as apiLogin } from "../../api/auth.service";
// import { AuthContext } from "../../context/AuthContext";

// export default function Login() {
//   const [usernameInput, setUsernameInput] = useState("");
//   const [password, setPassword] = useState("");
//   const { login } = useContext(AuthContext);

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const data = await apiLogin(usernameInput, password);
//       // ⚡ Incluimos role aquí
//       login(data.accessToken, data.username, data.role);
//     } catch (err) {
//       alert("Login error: " + (err.response?.data?.message || err.message));
//     }
//   };

//   return (
//     <div style={{ display: "flex", justifyContent: "center", alignItems: "center", width: "100vw", height: "100vh" }}>
//       <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "15px", width: "400px", padding: "40px", borderRadius: "12px", boxShadow: "0 4px 15px rgba(0,0,0,0.2)" }}>
//         <h2 style={{ textAlign: "center" }}>Login</h2>
//         <input placeholder="Username" value={usernameInput} onChange={(e) => setUsernameInput(e.target.value)} style={{ padding: "12px", borderRadius: "6px", border: "1px solid #ccc" }} />
//         <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} style={{ padding: "12px", borderRadius: "6px", border: "1px solid #ccc" }} />
//         <button type="submit" style={{ padding: "14px", borderRadius: "6px", backgroundColor: "#00aaff", color: "white", border: "none", cursor: "pointer" }}>Login</button>
//       </form>
//     </div>
//   );
// }

import { useState, useContext } from "react";
import { login as apiLogin } from "../../api/auth.service";
import { AuthContext } from "../../context/AuthContext";

export default function Login() {
  const [usernameInput, setUsernameInput] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useContext(AuthContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await apiLogin(usernameInput, password);
      
      // ⚡ Verificamos qué devuelve el backend
      console.log("Login response from API:", data);

      // ⚡ Incluimos role aquí
      login(data.accessToken, data.username, data.role);

      // ⚡ Verificamos que role se haya guardado
      console.log("Role sent to context:", data.role);
    } catch (err) {
      alert("Login error: " + (err.response?.data?.message || err.message));
    }
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", alignItems: "center", width: "100vw", height: "100vh" }}>
      <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "15px", width: "400px", padding: "40px", borderRadius: "12px", boxShadow: "0 4px 15px rgba(0,0,0,0.2)" }}>
        <h2 style={{ textAlign: "center" }}>Login</h2>
        <input placeholder="Username" value={usernameInput} onChange={(e) => setUsernameInput(e.target.value)} style={{ padding: "12px", borderRadius: "6px", border: "1px solid #ccc" }} />
        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} style={{ padding: "12px", borderRadius: "6px", border: "1px solid #ccc" }} />
        <button type="submit" style={{ padding: "14px", borderRadius: "6px", backgroundColor: "#00aaff", color: "white", border: "none", cursor: "pointer" }}>Login</button>
      </form>
    </div>
  );
}