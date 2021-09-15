import UserList from '@/components/UserList'
import Login from '@/components/Login'
import ResetPassword from '@/components/ResetPassword'

export const routes = [
  { path: '/', component: UserList },
  { path: '/login', component: Login },
  { path: '/resetpassword', component: ResetPassword }
]
