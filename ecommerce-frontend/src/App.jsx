import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider, AuthContext } from "./context/AuthContext";
import { CartProvider } from "./context/CartContext";
import Navbar from "./components/Navbar";

import Login from "./pages/Login/Login";
import Signup from "./pages/Signup/Signup";
import Orders from "./pages/Orders/Orders";
import Customers from "./pages/Customers/Customers";
import NotFound from "./pages/NotFound/NotFound";
import Home from "./pages/Home/Home";
import Manga from "./pages/Products/Manga";
import Figures from "./pages/Products/Figures";
import Apparel from "./pages/Products/Apparel";
import ProtectedRoute from "./components/ProtectedRoute";

// Items pages
import Items from "./pages/Items/Items";
import ItemDetail from "./pages/Items/ItemDetail";
import ItemForm from "./pages/Items/ItemForm";

// Utility
import CheckConnection from "./components/CheckConnection";
import { useContext } from "react";

// Role-protected route
function RoleProtectedRoute({ children, role }) {
  const { role: userRole } = useContext(AuthContext);
  if (userRole !== role) {
    return <Navigate to="/items" replace />;
  }
  return children;
}

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <CartProvider>
          <Navbar />
          <Routes>
            {/* Public pages */}
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />

            {/* Products pages */}
            <Route path="/products/manga" element={<Manga />} />
            <Route path="/products/figures" element={<Figures />} />
            <Route path="/products/apparel" element={<Apparel />} />

            {/* Items pages */}
            <Route path="/items" element={<Items />} />
            <Route
              path="/items/new"
              element={
                <RoleProtectedRoute role="ROLE_ADMIN">
                  <ItemForm />
                </RoleProtectedRoute>
              }
            />
            <Route
              path="/items/edit/:id"
              element={
                <RoleProtectedRoute role="ROLE_ADMIN">
                  <ItemForm />
                </RoleProtectedRoute>
              }
            />
            <Route path="/items/:id" element={<ItemDetail />} />

            {/* Health check */}
            <Route path="/health" element={<CheckConnection />} />

            {/* Protected pages */}
            <Route
              path="/orders"
              element={
                <ProtectedRoute>
                  <Orders />
                </ProtectedRoute>
              }
            />
            <Route
              path="/customers"
              element={
                <ProtectedRoute>
                  <Customers />
                </ProtectedRoute>
              }
            />

            {/* 404 page */}
            <Route path="*" element={<NotFound />} />
          </Routes>
        </CartProvider>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;