import { Link } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { CartContext } from "../context/CartContext";

export default function Navbar() {
  const { username, logout } = useContext(AuthContext);
  const { cart } = useContext(CartContext);

  const linkStyle = {
    textDecoration: "none",
    color: "white",
    position: "relative",
    transition: "color 0.3s"
  };

  const linkHoverStyle = {
    color: "#00ffff",
    textDecoration: "underline"
  };

  return (
    <header
      style={{
        position: "fixed",
        top: 0,
        left: 0,
        width: "100%",
        zIndex: 1000,
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        padding: "10px 20px",
        borderBottom: "1px solid #ccc",
        backgroundColor: "#000", // <-- Navbar opaco
        height: "60px",
        boxSizing: "border-box"
      }}
    >
      <div style={{ fontWeight: "bold", fontSize: "1.5rem" }}>
        <Link
          to="/"
          style={linkStyle}
          onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
          onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
        >
          Ecommerce
        </Link>
      </div>

      <nav style={{ display: "flex", gap: "20px", alignItems: "center" }}>
        {["manga", "figures", "apparel"].map((item) => (
          <Link
            key={item}
            to={`/products/${item}`}
            style={linkStyle}
            onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
            onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
          >
            {item.charAt(0).toUpperCase() + item.slice(1)}
          </Link>
        ))}

        <Link
          to="/items"
          style={linkStyle}
          onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
          onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
        >
          Items
        </Link>

        <Link
          to="/health"
          style={linkStyle}
          onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
          onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
        >
          Health
        </Link>
      </nav>

      <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
        {/* Carrito */}
        {username && (
          <Link
            to="/orders"
            style={{
              ...linkStyle,
              display: "flex",
              alignItems: "center",
              position: "relative"
            }}
            onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
            onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
          >
            🛒
            {cart.length > 0 && (
              <span
                style={{
                  position: "absolute",
                  top: "-5px",
                  right: "-10px",
                  backgroundColor: "red",
                  color: "white",
                  borderRadius: "50%",
                  padding: "2px 6px",
                  fontSize: "0.8rem"
                }}
              >
                {cart.length}
              </span>
            )}
          </Link>
        )}

        {username ? (
          <>
            <span style={{ color: "white" }}>Hello, {username}</span>
            <button
              onClick={logout}
              style={{
                padding: "6px 12px",
                border: "none",
                borderRadius: "5px",
                backgroundColor: "#00ffff",
                cursor: "pointer",
                fontWeight: "bold"
              }}
            >
              Logout
            </button>
          </>
        ) : (
          <>
            <Link
              to="/login"
              style={linkStyle}
              onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
              onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
            >
              Login
            </Link>
            <Link
              to="/signup"
              style={linkStyle}
              onMouseEnter={(e) => Object.assign(e.target.style, linkHoverStyle)}
              onMouseLeave={(e) => Object.assign(e.target.style, linkStyle)}
            >
              Sign Up
            </Link>
          </>
        )}
      </div>
    </header>
  );
}