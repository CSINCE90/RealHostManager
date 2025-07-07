// src/App.jsx
import React from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import LoginPage from './pages/LoginPage'
import Dashboard from './pages/Dashboard'
import UserManagement from './pages/UserManagement'
import StructurePage from './pages/StructurePage'
import BookingPage from './pages/BookingPage'
import GuestPage from './pages/GuestPage'
import AlloggiatiPage from './pages/AlloggiatiPage'
import Navbar from './components/Navbar'

const App = () => {
  const token = localStorage.getItem('token')

  return (
    <Router>
      {token && <Navbar />}
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/admin/users" element={<UserManagement />} />
        <Route path="/structures" element={<StructurePage />} />
        <Route path="/bookings" element={<BookingPage />} />
        <Route path="/guests" element={<GuestPage />} />
        <Route path="/alloggiati" element={<AlloggiatiPage />} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </Router>
  )
}

export default App
