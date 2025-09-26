import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import Navbar from "./components/Navbar";

import Login from "./pages/Login/Login";
import Orders from "./pages/Orders/Orders";
import Customers from "./pages/Customers/Customers";
import NotFound from "./pages/NotFound/NotFound";

import Manga from "./pages/Products/Manga";
import Figures from "./pages/Products/Figures";
import Apparel from "./pages/Products/Apparel";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Navbar />
        <Routes>
          <Route path="/login" element={<Login />} />

          {/* Product routes */}
          <Route path="/products/manga" element={<Manga />} />
          <Route path="/products/figures" element={<Figures />} />
          <Route path="/products/apparel" element={<Apparel />} />

          {/* Protected routes */}
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

          <Route path="*" element={<NotFound />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;