import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Home from "@/components/Home";
import LoginPage from "@/components/login";
import SignupPage from "@/components/signup";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path:"/home",
      name:"Home",
      component: Home
    },
    {
      path: "/login",
      name:"login",
      component: LoginPage
    },
    {
      path:"/signup",
      name:"signup",
      component:SignupPage
    }
  ]
});
