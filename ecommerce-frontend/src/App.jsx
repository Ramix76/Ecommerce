import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
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

// 👇 NUEVOS IMPORTS
import Items from "./pages/Items/Items";
import ItemDetail from "./pages/Items/ItemDetail";
import ItemForm from "./pages/Items/ItemForm";
import CheckConnection from "./components/CheckConnection";

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
            <Route path="/signup" element={<Signup />} />

            {/* Products */}
            <Route path="/products/manga" element={<Manga />} />
            <Route path="/products/figures" element={<Figures />} />
            <Route path="/products/apparel" element={<Apparel />} />

            {/* Items */}
            <Route path="/items" element={<Items />} />
            <Route path="/items/new" element={<ItemForm />} />
            <Route path="/items/edit/:id" element={<ItemForm />} />
            <Route path="/items/:id" element={<ItemDetail />} />

            {/* Health check */}
            <Route path="/health" element={<CheckConnection />} />

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