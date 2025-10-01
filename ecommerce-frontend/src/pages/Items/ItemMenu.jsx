import { useNavigate } from "react-router-dom";

export default function ItemMenu({ setMode }) {
  const navigate = useNavigate();

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        width: "100vw",
        height: "100vh",
        padding: "20px",
        boxSizing: "border-box",
      }}
    >
      <div style={{ maxWidth: "600px", width: "100%", textAlign: "center" }}>
        <h1>Items Management</h1>
        <p>What do you want to do?</p>
        <div
          style={{
            display: "flex",
            gap: "20px",
            marginTop: "20px",
            flexWrap: "wrap",
            justifyContent: "center",
          }}
        >
          <button onClick={() => setMode("search")}>Search Items</button>
          <button onClick={() => setMode("list")}>Show All Items</button>
          <button onClick={() => navigate("/items/new")}>Create New Item</button>
        </div>
      </div>
    </div>
  );
}