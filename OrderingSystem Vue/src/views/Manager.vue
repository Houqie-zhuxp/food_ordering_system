<template>
  <div>
    <div
      style="height: 60px; background-color: #fff; display: flex; align-items: center; border-bottom: 1px solid #ddd">
      <div style="flex: 1">
        <div style="padding-left: 20px; display: flex; align-items: center">
          <img src="@/assets/imgs/logo.png" alt="" style="width: 40px">
          <div style="font-weight: bold; font-size: 34px; margin-left: 5px;">在线点餐系统</div>
        </div>
      </div>
      <div style="width: fit-content; padding-right: 10px; display: flex; align-items: center;">
        <img v-if="data.user.avatar" :src="data.user.avatar" class="avatar"
          style="width: 50px; height: 50px; border-radius: 50%" />
        <img v-else :src="'http://localhost:9441/files/avatar001.jpg'" class="avatar"
          style="width: 50px; height: 50px; border-radius: 50%" />
        <span style="margin-left: 5px">{{ data.user.name }}</span>
      </div>
    </div>

    <div style="display: flex; background-color: rgb(84,92,100);">
      <div style="width: 200px; border-right: 1px solid #ddd; min-height: calc(100vh - 60px)">
        <el-menu router style="border: none" :default-active="$route.path" background-color="rgb(84,92,100)" text-color="white"
          active-text-color="grey" :default-openeds="['/home']">
          <el-menu-item index="/home">
            <i class="iconfont icon-r-home" style="font-size: 26px;margin-right: 5px;"></i>
            <span style="font-size: 22px;">首页</span>
          </el-menu-item>

          <el-menu-item index="/order" v-if="data.user.role === 'USER'">
            <i class="iconfont icon-r-shield" style="font-size: 26px;margin-right: 5px;"></i>
            <span style="font-size: 22px;">我的餐桌</span>
          </el-menu-item>

          <el-sub-menu index="2" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <i class="iconfont icon-r-yes" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">点餐管理</span>
            </template>
            <el-menu-item index="/tables">
              <i class="iconfont icon-r-shield" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">餐桌管理</span>
            </el-menu-item>
            <el-menu-item index="/foods">
              <i class="iconfont icon-r-mark1" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">餐品管理</span>
            </el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <i class="iconfont icon-r-team" style="font-size: 28px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">用户管理</span>
            </template>
            <el-menu-item index="/admin">
              <i class="iconfont icon-r-user1" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">管理员信息</span>
            </el-menu-item>
            <el-menu-item index="/user">
              <i class="iconfont icon-r-user2" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">用户信息</span>
            </el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>
              <i class="iconfont icon-r-user3" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">个人信息</span>
            </template>
            <el-menu-item index="/person">
              <i class="iconfont icon-r-edit" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">个人资料</span>
            </el-menu-item>
            <el-menu-item index="/myOrders">
              <i class="iconfont icon-r-paper" style="font-size: 26px;margin-right: 5px;"></i>
              <span style="font-size: 22px;">订单管理</span>
            </el-menu-item>
          </el-sub-menu>

          <el-menu-item index="/login" @click="logout">
            <i class="iconfont icon-r-left" style="font-size: 26px;margin-right: 5px;"></i>
            <span style="font-size: 22px;">退出系统</span>
          </el-menu-item>
        </el-menu>
      </div>

      <div style="flex: 1; width: 0; background-color: #f8f8ff; padding: 10px">
        <router-view @updateUser="updateUser" />
      </div>
    </div>

  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { User, UserFilled } from "@element-plus/icons-vue";
import { reactive } from "vue";
const $route = useRoute()

const data = reactive({
  user: JSON.parse(localStorage.getItem('canteen-user') || '{}')
})

const logout = () => {

  localStorage.removeItem('student-user')

}

const updateUser = () => {
  data.user = JSON.parse(localStorage.getItem('canteen-user') || '{}')
}
</script>

<style scoped>
.el-menu-item.is-active {
  background-color: #e0e4ff !important;
}

.el-menu-item:hover {
  background-color: #e9eeff !important;
  color: #1450aa;
}

:deep(th) {
  color: #333;
}
</style>