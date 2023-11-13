<template>
  <div>
    <q-layout class="stacked-linear">
      <q-header elevated>
        <q-toolbar>
          <q-btn flat @click="drawer = !drawer" round dense icon="menu"></q-btn>
          <q-toolbar-title>KanjiLearner</q-toolbar-title>
          <q-btn
            class="q-mr-lg"
            icon="camera_front"
            href="http://localhost:9000/#/user"
          >
          </q-btn>
          <q-btn @click="showAuthModal = true"> Log in</q-btn>
        </q-toolbar>
      </q-header>

      <q-drawer
        v-model="drawer"
        show-if-above
        :width="150"
        bordered
        overlay
        :breakpoint="500"
        class="bg-light-green-5"
      >
        <q-scroll-area class="fit">
          <q-list padding class="menu-list">
            <q-item href="http://localhost:9000/#/allw" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="inbox" />
              </q-item-section>

              <q-item-section> All words </q-item-section>
            </q-item>

            <q-item href="http://localhost:9000/#/" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="star" />
              </q-item-section>

              <q-item-section> Home </q-item-section>
            </q-item>

            <q-item href="http://localhost:9000/#/generator" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="send" />
              </q-item-section>

              <q-item-section> Generator </q-item-section>
            </q-item>

            <q-item href="http://localhost:9000/#/decks" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="folder_copy"/>
              </q-item-section>

              <q-item-section> Decks</q-item-section>
            </q-item>
          </q-list>
        </q-scroll-area>
      </q-drawer>

      <q-dialog v-model="showAuthModal">
        <auth-modal></auth-modal>
      </q-dialog>

      <router-view/>
    </q-layout>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {APIApi} from "../../../generated/";
import AuthModal from "components/modals/AuthModal.vue";

const api = new APIApi();
const drawer = ref(false);
const showAuthModal = ref(false);
</script>

<style lang="sass" scoped>

.back
  background: rgba(0, 44, 155, 0.51)
</style>

<style lang="css" scoped>
.stacked-linear {
  background: linear-gradient(
    217deg,
    rgba(145, 44, 44, 0.8),
    rgba(255, 0, 0, 0) 70.71%
  ),
  linear-gradient(127deg, rgba(42, 194, 42, 0.8), rgba(0, 255, 0, 0) 70.71%),
  linear-gradient(336deg, rgba(27, 27, 114, 0.8), rgba(0, 0, 255, 0) 70.71%);
}
</style>
