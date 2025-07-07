import React, { useEffect, useState } from 'react'
import jwtDecode from 'jwt-decode'
import { useNavigate } from 'react-router-dom'

const Dashboard = () => {
  const [role, setRole] = useState('')
  const navigate = useNavigate()

  useEffect(() => {
    const token = localStorage.getItem('token')
    if (!token) {
      navigate('/')
    } else {
      const decoded = jwtDecode(token)
      setRole(decoded.role || 'HOSTMANAGER')
    }
  }, [])

  return (
    <div className="container py-5">
      <h2 className="mb-4">🎉 Benvenuto in RealHostManager</h2>
      <div className="alert alert-info">
        Sei loggato come: <strong>{role}</strong>
      </div>

      <div className="row mt-4">
        {role === 'ADMIN' && (
          <div className="col-md-4">
            <div className="card shadow-sm">
              <div className="card-body">
                <h5 className="card-title">👤 Gestione Utenti</h5>
                <p className="card-text">Crea o modifica utenti Host Manager.</p>
                <button className="btn btn-outline-primary btn-sm">Vai</button>
              </div>
            </div>
          </div>
        )}

        <div className="col-md-4">
          <div className="card shadow-sm">
            <div className="card-body">
              <h5 className="card-title">🏨 Strutture</h5>
              <p className="card-text">Visualizza e gestisci le tue strutture.</p>
              <button className="btn btn-outline-success btn-sm">Vai</button>
            </div>
          </div>
        </div>

        <div className="col-md-4">
          <div className="card shadow-sm">
            <div className="card-body">
              <h5 className="card-title">📆 Prenotazioni</h5>
              <p className="card-text">Consulta e modifica le prenotazioni.</p>
              <button className="btn btn-outline-secondary btn-sm">Vai</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Dashboard
