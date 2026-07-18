import React, { useEffect, useState } from 'react'

export default function Crm() {
  const [customers, setCustomers] = useState([])
  const [locations, setLocations] = useState([])
  const [jobs, setJobs] = useState([])
  const [form, setForm] = useState({ name: '', phone: '', email: '', address: '', title: '', query: '' })
  const [message, setMessage] = useState('')
  const [selectedCustomerId, setSelectedCustomerId] = useState(null)

  async function loadData() {
    try {
      const [customersRes, locationsRes, jobsRes] = await Promise.all([
        fetch('http://localhost:8080/customers'),
        fetch('http://localhost:8080/locations'),
        fetch('http://localhost:8080/jobs')
      ])

      const [customersData, locationsData, jobsData] = await Promise.all([
        customersRes.ok ? customersRes.json() : [],
        locationsRes.ok ? locationsRes.json() : [],
        jobsRes.ok ? jobsRes.json() : []
      ])

      setCustomers(Array.isArray(customersData) ? customersData : [])
      setLocations(Array.isArray(locationsData) ? locationsData : [])
      setJobs(Array.isArray(jobsData) ? jobsData : [])
    } catch (error) {
      setCustomers([])
      setLocations([])
      setJobs([])
      setMessage('Unable to load CRM data right now.')
    }
  }

  useEffect(() => {
    loadData()
  }, [])

  async function searchCustomers() {
    try {
      const res = await fetch(`http://localhost:8080/customers?query=${encodeURIComponent(form.query)}`)
      const data = await res.json()
      setCustomers(Array.isArray(data) ? data : [])
    } catch (error) {
      setCustomers([])
    }
  }

  async function handleSubmit(e) {
    e.preventDefault()
    setMessage('Creating records...')

    try {
      const customerRes = await fetch('http://localhost:8080/customers', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: form.name, phone: form.phone, email: form.email })
      })
      const customer = await customerRes.json()

      const locationRes = await fetch('http://localhost:8080/locations', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ customerId: customer.id, address: form.address })
      })
      const location = await locationRes.json()

      const jobRes = await fetch('http://localhost:8080/jobs', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ customerId: customer.id, locationId: location.id, title: form.title })
      })
      const job = await jobRes.json()

      setMessage(`Created customer, location, and job: ${job.title}`)
      setForm({ name: '', phone: '', email: '', address: '', title: '' })
      await loadData()
    } catch (error) {
      setMessage('Unable to create records right now.')
    }
  }

  return (
    <div className="crm-panel">
      <div className="panel-header premium-header">
        <div>
          <p className="eyebrow">CRM workspace</p>
          <h3>Service operations intake</h3>
        </div>
        {message && <p className="status-message">{message}</p>}
      </div>

      <section className="mini-card hero-summary-card">
        <div>
          <p className="eyebrow">Live customer profile</p>
          <h4>{customers.find(c => c.id === selectedCustomerId)?.name || 'Select or create a customer'}</h4>
          <p>{customers.find(c => c.id === selectedCustomerId)?.email || 'Search for an existing customer or create a new one to begin.'}</p>
        </div>
        <div className="hero-metrics">
          <div>
            <strong>{customers.length}</strong>
            <span>customers</span>
          </div>
          <div>
            <strong>{locations.length}</strong>
            <span>locations</span>
          </div>
          <div>
            <strong>{jobs.length}</strong>
            <span>jobs</span>
          </div>
        </div>
      </section>

      <section className="mini-card crm-search-card">
        <div className="search-row">
          <input placeholder="Search customers" value={form.query} onChange={(e) => setForm({ ...form, query: e.target.value })} />
          <button type="button" onClick={searchCustomers}>Find</button>
        </div>
        <div className="customer-table-wrap">
          <table className="customer-table">
            <tbody>
              {customers.length > 0 ? customers.slice(0, 1).map(customer => (
                <tr key={customer.id} onClick={() => setSelectedCustomerId(customer.id)} className="customer-row">
                  <td><strong>{customer.name}</strong></td>
                  <td>{customer.phone}</td>
                  <td>{customer.email}</td>
                </tr>
              )) : (
                <tr>
                  <td colSpan="3">No customer matched your search</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </section>

      <form onSubmit={handleSubmit} className="crm-form premium-form">
        <div className="field-group">
          <input placeholder="Customer name" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} />
          <input placeholder="Phone" value={form.phone} onChange={(e) => setForm({ ...form, phone: e.target.value })} />
          <input placeholder="Email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} />
          <input placeholder="Service address" value={form.address} onChange={(e) => setForm({ ...form, address: e.target.value })} />
          <input placeholder="Job title" value={form.title} onChange={(e) => setForm({ ...form, title: e.target.value })} />
        </div>
        <button type="submit">Create records</button>
      </form>

      <div className="cards-grid premium-grid">
        <section className="mini-card">
          <h4>Customer context</h4>
          <p>{customers.find(c => c.id === selectedCustomerId)?.name || 'Choose a customer to see their context.'}</p>
        </section>
        <section className="mini-card">
          <h4>Service locations</h4>
          <ul>{locations.filter(l => !selectedCustomerId || l.customer?.id === selectedCustomerId).map(l => <li key={l.id}>{l.address}</li>)}</ul>
        </section>
        <section className="mini-card">
          <h4>Recent jobs</h4>
          <ul>{jobs.slice(0, 3).map(j => <li key={j.id}>{j.title} ({j.status})</li>)}</ul>
        </section>
      </div>
    </div>
  )
}
