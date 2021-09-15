import UserList from '@/components/UserList'
import Login from '@/components/Login'
import ResetPassword from '@/components/ResetPassword'
import BlankPage from '@/components/BlankPage'

export const routes = [
  { path: '/', component: UserList },
  { path: '/gui/', component: UserList },
  { path: '/gui/login', component: Login },
  { path: '/gui/resetpassword', component: ResetPassword },
  { path: '/gui/blankpage', component: BlankPage },
  { path: '/wrong/blankpage', component: BlankPage }
]
