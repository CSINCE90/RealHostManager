
import React, { useEffect, useState } from 'react'
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

const BookingPage = () => {
  const [bookings, setBookings] = useState([])
  const [form, setForm] = useState({ guestName: '', date: '', structureId: '' })
  const [editingId, setEditingId] = useState(null)

  const fetchBookings = async () => {
    try {
      const res = await api.get('/bookings')
      setBookings(res.data)
    } catch (err) {
      console.error('Errore nel recupero prenotazioni:', err)
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (editingId) {
        await api.put(`/bookings/${editingId}`, form)
      } else {
        await api.post('/bookings', form)
      }
      setForm({ guestName: '', date: '', structureId: '' })
      setEditingId(null)
      fetchBookings()
    } catch (err) {
      console.error('Errore nel salvataggio prenotazione:', err)
    }
  }

  const handleEdit = (b) => {
    setForm({ guestName: b.guestName, date: b.date, structureId: b.structureId })
    setEditingId(b.id)
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Eliminare questa prenotazione?')) return
    try {
      await api.delete(`/bookings/${id}`)
      fetchBookings()
    } catch (err) {
      console.error('Errore nella cancellazione prenotazione:', err)
    }
  }

  useEffect(() => {
    fetchBookings()
  }, [])

  return (
    <div className="container py-4">
      <h2>Gestione Prenotazioni</h2>

      <form className="mb-4" onSubmit={handleSubmit}>
        <div className="row g-3">
          <div className="col-md-4">
            <input className="form-control" placeholder="Nome Ospite" value={form.guestName}
              onChange={e => setForm({ ...form, guestName: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input type="date" className="form-control" value={form.date}
              onChange={e => setForm({ ...form, date: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="ID Struttura" value={form.structureId}
              onChange={e => setForm({ ...form, structureId: e.target.value })} required />
          </div>
          <div className="col-md-2">
            <button className="btn btn-primary w-100" type="submit">
              {editingId ? 'Aggiorna' : 'Aggiungi'}
            </button>
          </div>
        </div>
      </form>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>Ospite</th>
            <th>Data</th>
            <th>ID Struttura</th>
            <th>Azioni</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map(b => (
            <tr key={b.id}>
              <td>{b.guestName}</td>
              <td>{b.date}</td>
              <td>{b.structureId}</td>
              <td>
                <button className="btn btn-sm btn-outline-secondary me-2" onClick={() => handleEdit(b)}>✏️</button>
                <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(b.id)}>🗑️</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default BookingPage
