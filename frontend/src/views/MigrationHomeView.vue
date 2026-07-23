<script setup lang="ts">
import { useRouter } from 'vue-router'

import addAccountIcon from '@/assets/legacy/addfile.png'
import depositIcon from '@/assets/legacy/deposit.png'
import historyIcon from '@/assets/legacy/history.png'
import logoutIcon from '@/assets/legacy/logout.png'
import transferIcon from '@/assets/legacy/transfer.png'
import userInfoIcon from '@/assets/legacy/userinfo.png'
import withdrawIcon from '@/assets/legacy/withdraw.png'
import LegacyBankingLayout from '@/components/LegacyBankingLayout.vue'
import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()
const router = useRouter()
const menuItems = [
  { label: 'New Account', icon: addAccountIcon },
  { label: 'Withdraw Money', icon: withdrawIcon },
  { label: 'Deposit Money', icon: depositIcon },
  { label: 'Transfer Money', icon: transferIcon },
  { label: 'Transaction History', icon: historyIcon },
]

async function logout() {
  await authStore.logout()
  await router.push({ name: 'login' })
}
</script>

<template>
  <LegacyBankingLayout>
    <fieldset class="legacy-fieldset legacy-fieldset--account">
      <div class="account-north">
        <span class="account-greeting">
          {{ authStore.user?.firstName }}, welcome to your account!
        </span>
        <span class="account-user-actions">
          <button class="account-user-action" type="button" disabled>
            <img :src="userInfoIcon" alt="" />
            User Profile
          </button>
          <button class="account-user-action" type="button" @click="logout">
            <img :src="logoutIcon" alt="" />
            Logout
          </button>
        </span>
      </div>

      <div class="account-layout">
        <nav class="account-menu" aria-label="Bank account functions">
          <button
            v-for="item in menuItems"
            :key="item.label"
            class="account-menu-button"
            type="button"
            disabled
            title="This function will be enabled in its migration stage."
          >
            <img :src="item.icon" alt="" />
            {{ item.label }}
          </button>
        </nav>

        <section class="account-main">
          <table class="account-table">
            <caption>
              Bank Account Summary
            </caption>
            <thead>
              <tr>
                <th scope="col">Account Id</th>
                <th scope="col">Account Type</th>
                <th scope="col">Current Balance</th>
                <th scope="col">Time of Created</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="account-table-empty" colspan="4">No records found.</td>
              </tr>
            </tbody>
          </table>
        </section>
      </div>
    </fieldset>
  </LegacyBankingLayout>
</template>
