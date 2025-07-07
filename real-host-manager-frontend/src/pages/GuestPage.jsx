
import React, { useEffect, useState } from 'react'
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

const GuestPage = () => {
  const [guests, setGuests] = useState([])
  const [form, setForm] = useState({ name: '', surname: '', document: '' })
  const [editingId, setEditingId] = useState(null)

  const fetchGuests = async () => {
    try {
      const res = await api.get('/guests')
      setGuests(res.data)
    } catch (err) {
      console.error('Errore nel recupero ospiti:', err)
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (editingId) {
        await api.put(`/guests/${editingId}`, form)
      } else {
        await api.post('/guests', form)
      }
      setForm({ name: '', surname: '', document: '' })
      setEditingId(null)
      fetchGuests()
    } catch (err) {
      console.error('Errore nel salvataggio ospite:', err)
    }
  }

  const handleEdit = (guest) => {
    setForm({ name: guest.name, surname: guest.surname, document: guest.document })
    setEditingId(guest.id)
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Eliminare questo ospite?')) return
    try {
      await api.delete(`/guests/${id}`)
      fetchGuests()
    } catch (err) {
      console.error('Errore nella cancellazione ospite:', err)
    }
  }

  useEffect(() => {
    fetchGuests()
  }, [])

  return (
    <div className="container py-4">
      <h2>Gestione Ospiti</h2>

      <form className="mb-4" onSubmit={handleSubmit}>
        <div className="row g-3">
          <div className="col-md-3">
            <input className="form-control" placeholder="Nome" value={form.name}
              onChange={e => setForm({ ...form, name: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Cognome" value={form.surname}
              onChange={e => setForm({ ...form, surname: e.target.value })} required />
          </div>
          <div className="col-md-4">
            <input className="form-control" placeholder="Documento" value={form.document}
              onChange={e => setForm({ ...form, document: e.target.value })} required />
          </div>
          <div className="col-md-2">
            <button className="btn btn-primary w-100" type="submit">
              {editingId ? 'Aggiorna' : 'Aggiungi'}
            </button>
          </div>
        </div>
      </form>

      <table className="table table-hover">
        <thead>
          <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Documento</th>
            <th>Azioni</th>
          </tr>
        </thead>
        <tbody>
          {guests.map(g => (
            <tr key={g.id}>
              <td>{g.name}</td>
              <td>{g.surname}</td>
              <td>{g.document}</td>
              <td>
                <button className="btn btn-sm btn-outline-secondary me-2" onClick={() => handleEdit(g)}>✏️</button>
                <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(g.id)}>🗑️</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default GuestPage
