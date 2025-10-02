import { useContext } from "react";
import { AuthContext } from "../../context/AuthContext";

export default function Home() {
  const { username } = useContext(AuthContext);

  return (
    <div
      style={{
        width: "100vw",
        height: "100vh",
        backgroundImage: "url('/assets/manga-store.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        position: "relative",
      }}
    >
      {/* Overlay para oscurecer el fondo */}
      <div
        style={{
          position: "absolute",
          top: 0,
          left: 0,
          width: "100%",
          height: "100%",
          backgroundColor: "rgba(0, 0, 0, 0.5)",
        }}
      ></div>

      {/* Contenido centrado */}
      <div
        style={{
          position: "relative",
          color: "white",
          textAlign: "center",
          padding: "20px",
        }}
      >
        <h1 style={{ fontSize: "3rem", marginBottom: "20px" }}>
          {username ? `Welcome, ${username}!` : "Welcome to the store"}
        </h1>
        <p style={{ fontSize: "1.5rem" }}>
          {username
            ? "Check out our products below and add them to your order!"
            : "Select a category from the top menu to get started."}
        </p>
      </div>
    </div>
  );
}