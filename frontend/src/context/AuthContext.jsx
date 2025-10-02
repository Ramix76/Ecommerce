// import { createContext, useState } from "react";
// import { useNavigate } from "react-router-dom";

// export const AuthContext = createContext();

// export function AuthProvider({ children }) {
//   const navigate = useNavigate();
//   const [token, setToken] = useState(localStorage.getItem("token"));
//   const [username, setUsername] = useState(localStorage.getItem("username"));
//   const [role, setRole] = useState(localStorage.getItem("role"));

//   const login = (token, username, role) => {
//     localStorage.setItem("token", token);
//     localStorage.setItem("username", username);
//     localStorage.setItem("role", role);
//     setToken(token);
//     setUsername(username);
//     setRole(role);
//     navigate("/");
//   };

//   const logout = () => {
//     localStorage.removeItem("token");
//     localStorage.removeItem("username");
//     localStorage.removeItem("role");
//     setToken(null);
//     setUsername(null);
//     setRole(null);
//     navigate("/login");
//   };

//   return (
//     <AuthContext.Provider value={{ token, username, role, login, logout }}>
//       {children}
//     </AuthContext.Provider>
//   );
// }

import { createContext, useState } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const navigate = useNavigate();
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [username, setUsername] = useState(localStorage.getItem("username"));
  const [role, setRole] = useState(localStorage.getItem("role"));

  const login = (token, username, role) => {
    // ⚡ Debug para ver qué llega
    console.log("Login called with role:", role);

    localStorage.setItem("token", token);
    localStorage.setItem("username", username);
    localStorage.setItem("role", role);

    setToken(token);
    setUsername(username);
    setRole(role);

    navigate("/");
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    setToken(null);
    setUsername(null);
    setRole(null);
    navigate("/login");
  };

  return (
    <AuthContext.Provider value={{ token, username, role, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}