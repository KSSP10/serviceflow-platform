export async function getHealth() {
  const res = await fetch('http://localhost:8080/health', { cache: 'no-store' })
  if (!res.ok) throw new Error('health check failed')
  return res.text()
}
