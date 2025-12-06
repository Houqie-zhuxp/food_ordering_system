import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
// 导入对应包
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

import ElementPlus from 'unplugin-element-plus/vite'
import fs from 'fs'

const optimizeDepsElementPlusIncludes = ["element-plus/es"]
fs.readdirSync("node_modules/element-plus/es/components").map((dirname) => {
  optimizeDepsElementPlusIncludes.push(
    `element-plus/es/components/${dirname}/style/index`
  )
})
optimizeDepsElementPlusIncludes.push(
  `element-plus/es/components/message/style/css`,
  `element-plus/es/components/message-box/style/css`
)
console.log(optimizeDepsElementPlusIncludes)

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    host: 'localhost',
    port: 9442,
    hmr: false,
  },
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver(
        { importStyle: 'sass' }
      )],
    }),
    Components({
      resolvers: [ElementPlusResolver(
        { importStyle: 'sass' }
      )],
    }),

    // 按需定制主题配置
    ElementPlus({
      useSource: false,
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    preprocessorOptions: {
      scss: {
        // 自动导入定制化样式文件进行样式覆盖
        additionalData: `
          @use "@/assets/css/index.scss" as *;
        `,
      }
    }
  },
  optimizeDepsElementPlusIncludes: optimizeDepsElementPlusIncludes,
  optimizeDeps: {
    force: true,
    include: optimizeDepsElementPlusIncludes
  },
})
