import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import home from "@/components/home";
import login from "@/components/login";
import signup from "@/components/signup";
import LogoBar from "@/components/LogoBar";
import room from "@/components/room";
import tutor from "@/components/tutor";
import tutorApplication from "@/components/tutorApplication";
import commission from "@/components/commission";
import review from "@/components/review";
import student from "@/components/student";
import subjectRequest from "@/components/subjectRequest";
import home1 from "@/components/home1";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: "/home",
      name: "home",
      component: home
    },
    {
      path: "/login",
      name: "login",
      component: login
    },
    {
      path: "/signup",
      name: "signup",
      component: signup
    },
    {
      path: "/LogoBar",
      name: "LogoBar",
      component: LogoBar
    },
    {
      path: "/room",
      name: "room",
      component: room
    },
    {
      path: "/tutor",
      name: "tutor",
      component: tutor
    },
    {
      path: "/tutorApplication",
      name: "tutorApplication",
      component: tutorApplication
    },
    {
      path: "/commission",
      name: "commission",
      component: commission
    },
    {
      path: "/review",
      name: "review",
      component: review
    },
    {
      path: "/student",
      name: "student",
      component: student
    },
    {
      path: "/subjectRequest",
      name: "subjectRequest",
      component: subjectRequest
    },
    {
      path: "/home1",
      name: "home1",
      component: home1
    },
  ]
});
