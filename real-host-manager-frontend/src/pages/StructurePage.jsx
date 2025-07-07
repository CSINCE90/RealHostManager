
import React, { useEffect, useState } from 'react'
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

const StructurePage = () => {
  const [structures, setStructures] = useState([])
  const [form, setForm] = useState({ name: '', address: '', city: '', country: '' })
  const [editingId, setEditingId] = useState(null)

  const fetchStructures = async () => {
    try {
      const res = await api.get('/structures')
      setStructures(res.data)
    } catch (err) {
      console.error('Errore nel recupero strutture:', err)
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (editingId) {
        await api.put(`/structures/${editingId}`, form)
      } else {
        await api.post('/structures', form)
      }
      setForm({ name: '', address: '', city: '', country: '' })
      setEditingId(null)
      fetchStructures()
    } catch (err) {
      console.error('Errore nel salvataggio:', err)
    }
  }

  const handleEdit = (structure) => {
    setForm({
      name: structure.name,
      address: structure.address,
      city: structure.city,
      country: structure.country
    })
    setEditingId(structure.id)
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Sei sicuro di voler eliminare questa struttura?')) return
    try {
      await api.delete(`/structures/${id}`)
      fetchStructures()
    } catch (err) {
      console.error('Errore nella cancellazione:', err)
    }
  }

  useEffect(() => {
    fetchStructures()
  }, [])

  return (
    <div className="container py-4">
      <h2>Gestione Strutture</h2>

      <form className="mb-4" onSubmit={handleSubmit}>
        <div className="row g-3">
          <div className="col-md-3">
            <input className="form-control" placeholder="Nome" value={form.name}
              onChange={e => setForm({ ...form, name: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Indirizzo" value={form.address}
              onChange={e => setForm({ ...form, address: e.target.value })} required />
          </div>
          <div className="col-md-2">
            <input className="form-control" placeholder="Città" value={form.city}
              onChange={e => setForm({ ...form, city: e.target.value })} required />
          </div>
          <div className="col-md-2">
            <input className="form-control" placeholder="Nazione" value={form.country}
              onChange={e => setForm({ ...form, country: e.target.value })} required />
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
            <th>Nome</th>
            <th>Indirizzo</th>
            <th>Città</th>
            <th>Nazione</th>
            <th>Azioni</th>
          </tr>
        </thead>
        <tbody>
          {structures.map(s => (
            <tr key={s.id}>
              <td>{s.name}</td>
              <td>{s.address}</td>
              <td>{s.city}</td>
              <td>{s.country}</td>
              <td>
                <button className="btn btn-sm btn-outline-secondary me-2" onClick={() => handleEdit(s)}>✏️</button>
                <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(s.id)}>🗑️</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default StructurePage
