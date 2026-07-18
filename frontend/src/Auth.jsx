import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function Auth({ onLogin }) {
  const navigate = useNavigate()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [message, setMessage] = useState('')

  async function handleSubmit(e) {
    e.preventDefault()
    setMessage('Signing in...')

    try {
      const res = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      })

      const data = await res.json()
      if (res.ok) {
        onLogin?.({ username, ...data.user })
        setMessage(`Signed in as ${data.user.username} (${data.user.roles.join(', ')})`)
        navigate('/admin')
      } else {
        setMessage(data.message || 'Login failed')
      }
    } catch (error) {
      setMessage('Unable to reach the auth service.')
    }
  }

  return (
    <div style={{ fontFamily: 'Arial, Helvetica, sans-serif', padding: 24, maxWidth: 360, margin: '40px auto' }}>
      <h2>ServiceFlow Login</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: 12 }}>
          <label>Username</label><br />
          <input value={username} onChange={(e) => setUsername(e.target.value)} style={{ width: '100%', padding: 8 }} />
        </div>
        <div style={{ marginBottom: 12 }}>
          <label>Password</label><br />
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} style={{ width: '100%', padding: 8 }} />
        </div>
        <button type="submit" style={{ padding: '8px 12px' }}>Sign in</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  )
}
