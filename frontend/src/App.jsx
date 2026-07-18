import React, { useEffect, useState } from 'react'
import { getHealth } from './api/health'

export default function App() {
  const [status, setStatus] = useState('unknown')

  useEffect(() => {
    let mounted = true
    getHealth().then(text => { if (mounted) setStatus(text) }).catch(()=>{ if (mounted) setStatus('unreachable') })
    return () => { mounted = false }
  }, [])

  return (
    <div style={{fontFamily:'Arial,Helvetica,sans-serif',padding:20}}>
      <h1>ServiceFlow Frontend</h1>
      <p>Backend health: <strong>{status}</strong></p>
      <p>Next: implement auth and booking flows.</p>
    </div>
  )
}
