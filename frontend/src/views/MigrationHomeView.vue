<template>
  <main class="migration-home">
    <section>
      <p class="eyebrow">dxconlinebanking</p>
      <h1>Migration in progress</h1>
      <p>新系統骨架與空白 Oracle schema migration 已建立。下一個功能切片為註冊與登入。</p>
    </section>
  </main>
</template>

<style scoped>
.migration-home {
  min-height: 100vh;
  display: grid;
  place-items: center;
  padding: 2rem;
}

section {
  width: min(42rem, 100%);
  padding: 3rem;
  border: 1px solid #d9e2ef;
  border-radius: 1.25rem;
  background: white;
  box-shadow: 0 1rem 3rem rgb(31 55 90 / 8%);
}

.eyebrow {
  margin: 0;
  color: #2463a7;
  font-size: 0.8rem;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
}

h1 {
  margin: 0.6rem 0 1rem;
  font-size: clamp(2rem, 6vw, 3.5rem);
  line-height: 1.05;
}

p:last-child {
  margin: 0;
  color: #52617a;
  line-height: 1.7;
}
</style>
