import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { CartProvider } from "./context/CartContext";
import Navbar from "./components/Navbar";

import Login from "./pages/Login/Login";
import Orders from "./pages/Orders/Orders";
import Customers from "./pages/Customers/Customers";
import NotFound from "./pages/NotFound/NotFound";
import Home from "./pages/Home/Home";
import Manga from "./pages/Products/Manga";
import Figures from "./pages/Products/Figures";
import Apparel from "./pages/Products/Apparel";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <CartProvider>
          <Navbar />
          <Routes>
            {/* Public */}
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />

            {/* Products */}
            <Route path="/products/manga" element={<Manga />} />
            <Route path="/products/figures" element={<Figures />} />
            <Route path="/products/apparel" element={<Apparel />} />

            {/* Protected */}
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

            {/* 404 */}
            <Route path="*" element={<NotFound />} />
          </Routes>
        </CartProvider>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;