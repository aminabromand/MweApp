import UserList from '@/components/UserList'
import Login from '@/components/Login'
import ResetPassword from '@/components/ResetPassword'
import SetTokenPassword from '@/components/SetTokenPassword'
import BlankPage from '@/components/BlankPage'

export const routes = [
  { path: '/', component: UserList },
  { path: '/gui/', component: UserList },
  { name: 'Login', path: '/gui/login', component: Login, meta: { withoutAuth: true } },
  { path: '/gui/resetpassword', component: ResetPassword, meta: { withoutAuth: true } },
  { path: '/gui/settokenpassword', component: SetTokenPassword, meta: { withoutAuth: true } },
  { path: '/gui/blankpage', component: BlankPage, meta: { withoutAuth: true } },
  { path: '/wrong/blankpage', component: BlankPage, meta: { withoutAuth: true } }
]
