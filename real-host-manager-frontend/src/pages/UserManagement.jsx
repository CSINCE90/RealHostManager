
import React, { useEffect, useState } from 'react'
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

const UserManagement = () => {
  const [users, setUsers] = useState([])
  const [form, setForm] = useState({ email: '', password: '', role: 'HOSTMANAGER' })
  const [editingId, setEditingId] = useState(null)

  const fetchUsers = async () => {
    try {
      const res = await api.get('/auth/users')
      setUsers(res.data)
    } catch (err) {
      console.error('Errore nel recupero utenti:', err)
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (editingId) {
        await api.put(`/auth/users/${editingId}`, form)
      } else {
        await api.post('/auth/register', form)
      }
      setForm({ email: '', password: '', role: 'HOSTMANAGER' })
      setEditingId(null)
      fetchUsers()
    } catch (err) {
      console.error('Errore nel salvataggio utente:', err)
    }
  }

  const handleEdit = (user) => {
    setForm({ email: user.email, password: '', role: user.role })
    setEditingId(user.id)
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Eliminare questo utente?')) return
    try {
      await api.delete(`/auth/users/${id}`)
      fetchUsers()
    } catch (err) {
      console.error('Errore nella cancellazione utente:', err)
    }
  }

  useEffect(() => {
    fetchUsers()
  }, [])

  return (
    <div className="container py-4">
      <h2>Gestione Host Manager</h2>

      <form className="mb-4" onSubmit={handleSubmit}>
        <div className="row g-3">
          <div className="col-md-4">
            <input className="form-control" placeholder="Email" value={form.email}
              onChange={e => setForm({ ...form, email: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Password" type="password" value={form.password}
              onChange={e => setForm({ ...form, password: e.target.value })} required={!editingId} />
          </div>
          <div className="col-md-3">
            <select className="form-select" value={form.role}
              onChange={e => setForm({ ...form, role: e.target.value })}>
              <option value="HOSTMANAGER">HOSTMANAGER</option>
              <option value="ADMIN">ADMIN</option>
            </select>
          </div>
          <div className="col-md-2">
            <button className="btn btn-primary w-100" type="submit">
              {editingId ? 'Aggiorna' : 'Crea'}
            </button>
          </div>
        </div>
      </form>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Email</th>
            <th>Ruolo</th>
            <th>Azioni</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.email}</td>
              <td>{user.role}</td>
              <td>
                <button className="btn btn-sm btn-outline-secondary me-2" onClick={() => handleEdit(user)}>✏️</button>
                <button className="btn btn-sm btn-outline-danger" onClick={() => handleDelete(user.id)}>🗑️</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default UserManagement
