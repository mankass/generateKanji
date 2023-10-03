const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      { path: "/allw", component: () => import("pages/AllWords.vue") },
    ],
  },
  {
    path: "/all",
    component: () => import("layouts/AllWords.vue"),
    children: [],
  },

  {
    path: "/client",
    component: () => import("pages/UserPage.vue"),
    children: [],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
