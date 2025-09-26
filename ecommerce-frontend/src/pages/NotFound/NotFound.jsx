import React from "react";
import { useNavigate } from "react-router-dom";

export default function NotFound() {
  const navigate = useNavigate();

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>404 - Page not found 😅</h1>
      <p>La página que buscas no existe.</p>
      <button
        onClick={() => navigate("/home")}
        style={{
          marginTop: "20px",
          padding: "10px 15px",
          borderRadius: "8px",
          cursor: "pointer",
        }}
      >
        🔄 Go Home
      </button>
    </div>
  );
}