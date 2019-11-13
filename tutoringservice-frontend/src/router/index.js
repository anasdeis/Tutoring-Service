import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import home from "@/components/home";
import login from "@/components/login";
import signup from "@/components/signup";

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
      name:"home",
      component: home
    },
    {
      path: "/login",
      name:"login",
      component: login
    },
    {
      path:"/signup",
      name:"signup",
      component:signup
    }
  ]
});
