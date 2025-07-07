import React, { useState } from 'react'
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

const AlloggiatiPage = () => {
  const [form, setForm] = useState({
    nome: '',
    cognome: '',
    sesso: '',
    dataNascita: '',
    luogoNascita: '',
    cittadinanza: '',
    tipoDocumento: '',
    numeroDocumento: '',
    rilasciatoDaStato: '',
    dataArrivo: '',
    comuneAlloggio: '',
    capoFamiglia: ''
  })

  const [response, setResponse] = useState(null)
  const [error, setError] = useState(null)
  const [showPreview, setShowPreview] = useState(false)
  const [xmlPreview, setXmlPreview] = useState('')

  const handlePreview = async () => {
    try {
      const res = await api.post('/alloggiati/genera-xml', form)
      setXmlPreview(res.data)
      setShowPreview(true)
    } catch (err) {
      setXmlPreview("Errore nella generazione dell'anteprima XML.")
      setShowPreview(true)
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const res = await api.post('/alloggiati/invia', form)
      setResponse(res.data)
      setError(null)
    } catch (err) {
      setError("Errore nell'invio della schedina.")
      setResponse(null)
      console.error(err)
    }
  }

  const handleStorico = async () => {
    try {
      const res = await api.get('/alloggiati/storico')
      setResponse(res.data)
      setError(null)
    } catch (err) {
      setError("Errore nel recupero dello storico.")
      setResponse(null)
      console.error(err)
    }
  }

  return (
    <div className="container py-4">
      <h2>Invio schedine Alloggiati Web (XML)</h2>
      <form onSubmit={handleSubmit} className="mb-4">
        <div className="row g-3">
          <div className="col-md-3">
            <input className="form-control" placeholder="Nome" value={form.nome}
              onChange={e => setForm({ ...form, nome: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Cognome" value={form.cognome}
              onChange={e => setForm({ ...form, cognome: e.target.value })} required />
          </div>
          <div className="col-md-2">
            <select className="form-select" value={form.sesso}
              onChange={e => setForm({ ...form, sesso: e.target.value })} required>
              <option value="">Seleziona sesso</option>
              <option value="M">Maschio</option>
              <option value="F">Femmina</option>
            </select>
          </div>
          <div className="col-md-2">
            <input type="date" className="form-control" value={form.dataNascita}
              onChange={e => setForm({ ...form, dataNascita: e.target.value })} required />
          </div>
          <div className="col-md-2">
            <input className="form-control" placeholder="Luogo nascita" value={form.luogoNascita}
              onChange={e => setForm({ ...form, luogoNascita: e.target.value })} required />
          </div>

          <div className="col-md-3">
            <input className="form-control" placeholder="Cittadinanza (es. ITA)" value={form.cittadinanza}
              onChange={e => setForm({ ...form, cittadinanza: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <select className="form-select" value={form.tipoDocumento}
              onChange={e => setForm({ ...form, tipoDocumento: e.target.value })} required>
              <option value="">Seleziona documento</option>
              <option value="C">Carta Identità</option>
              <option value="P">Passaporto</option>
              <option value="I">Patente</option>
            </select>
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Numero documento" value={form.numeroDocumento}
              onChange={e => setForm({ ...form, numeroDocumento: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Stato rilascio (es. ITA)" value={form.rilasciatoDaStato}
              onChange={e => setForm({ ...form, rilasciatoDaStato: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input type="date" className="form-control" value={form.dataArrivo}
              onChange={e => setForm({ ...form, dataArrivo: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <input className="form-control" placeholder="Comune alloggio" value={form.comuneAlloggio}
              onChange={e => setForm({ ...form, comuneAlloggio: e.target.value })} required />
          </div>
          <div className="col-md-3">
            <label className="form-label fw-bold">Capo Famiglia</label>
            <small className="text-muted d-block mb-1">
              Seleziona se l'ospite è il rappresentante del gruppo (capofamiglia).
            </small>
            <select className="form-select" value={form.capoFamiglia}
              onChange={e => setForm({ ...form, capoFamiglia: e.target.value === 'true' })} required>
              <option value="">È capo famiglia?</option>
              <option value="true">Sì</option>
              <option value="false">No</option>
            </select>
          </div>
          <div className="col-md-3">
            <button type="button" className="btn btn-outline-primary w-100" onClick={handlePreview}>
              Anteprima XML
            </button>
          </div>
          <div className="col-md-3">
            <button type="submit" className="btn btn-success w-100">Invia</button>
          </div>
          <div className="col-md-3">
            <button type="button" className="btn btn-info w-100" onClick={handleStorico}>
              Visualizza Storico
            </button>
          </div>
        </div>
      </form>

      {showPreview && (
        <div className="alert alert-secondary mt-4">
          <h5>Anteprima XML</h5>
          <pre style={{ whiteSpace: 'pre-wrap' }}>{xmlPreview}</pre>
        </div>
      )}
      {response && (
        <div className="alert alert-success">
          ✅ Schedina inviata correttamente!<br />
          <strong>Risposta:</strong> {JSON.stringify(response)}
        </div>
      )}
      {error && (
        <div className="alert alert-danger">
          ❌ {error}
        </div>
      )}
    </div>
  )
}

export default AlloggiatiPage
