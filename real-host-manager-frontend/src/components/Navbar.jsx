import React from 'react'
import { Link } from 'react-router-dom'

const Navbar = () => {
  const handleLogout = () => {
    localStorage.removeItem('token')
    window.location.href = '/'
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light px-3">
      <Link className="navbar-brand" to="/dashboard">🏠 RHM</Link>
      <div className="collapse navbar-collapse">
        <ul className="navbar-nav me-auto">
          <li className="nav-item"><Link className="nav-link" to="/structures">Strutture</Link></li>
          <li className="nav-item"><Link className="nav-link" to="/bookings">Prenotazioni</Link></li>
          <li className="nav-item"><Link className="nav-link" to="/guests">Ospiti</Link></li>
          <li className="nav-item"><Link className="nav-link" to="/alloggiati">Alloggiati</Link></li>
          <li className="nav-item"><Link className="nav-link" to="/admin/users">Utenti</Link></li>
        </ul>
        <button className="btn btn-outline-danger btn-sm" onClick={handleLogout}>Logout</button>
      </div>
    </nav>
  )
}

export default Navbar