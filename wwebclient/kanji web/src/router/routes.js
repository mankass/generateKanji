const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/MainPage.vue") },
      { path: "/allw", component: () => import("pages/AllWords.vue") },
      { path: "/decks", component: () => import("pages/DecksPage.vue") },
      { path: "/user", component: () => import("pages/UserPage.vue") },
      {
        path: "/generator",
        component: () => import("pages/GeneratorPage.vue"),
      },
    ],
  },
  {
    path: "/all",
    component: () => import("layouts/AllWords.vue"),
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
