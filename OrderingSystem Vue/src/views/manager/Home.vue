<template>
  <div>

    <div class="card">
      <span class="welcome" style="font-size: 24px;">
        <i class="iconfont icon-r-user3" style="font-size: 34px;"></i>
        欢迎使用，{{ user.name }}
      </span>
    </div>

    <div class="card" v-if="user.role === 'ADMIN'">
      <img src="@/assets/imgs/body.jpg" style="height: 70vh">
    </div>
    <div class="card" v-if="user.role === 'USER'">
      <div style="display: flex;flex-wrap: wrap">
        <div v-for="item in data.tables" :key="item.id"
          style="text-align: center;margin-right: 30px;margin-bottom: 30px; margin-bottom: 30px">
          <div><img src="@/assets/imgs/diancan.png" alt="" style="width: 160px;"></div>
          <div>{{ item.no }}</div>
          <div style="margin: 10px 0">
            <span style="color:#04c46d;font-size: 20px;" v-if="item.free === '是'"><i class="iconfont icon-r-refresh" style="font-size: 22px; margin-right: 1px;"></i>  空闲</span>
            <span style="color:#b20130;font-size: 20px;" v-else><i class="iconfont icon-r-no" style="font-size: 22px; margin-right: 1px;"></i> 占用</span>
          </div>
          <div v-if="item.free === '是'">
            <el-button type="success" @click="addOrder(item)" style="font-size: 18px;">
              <i class="iconfont icon-r-yes" style="font-size: 22px;margin-right: 5px;"></i>
               开始点餐
            </el-button>
          </div>
          <div v-if="item.free === '否'">
            <el-button disabled type="danger" @click="addOrder(item)" style="font-size: 18px;">
              <i class="iconfont icon-r-yes" style="font-size: 22px;margin-right: 5px;"></i>
               开始点餐
            </el-button>
          </div>
        </div>

      </div>

    </div>

  </div>
</template>

<script setup>
import request from "@/utils/request";
import { reactive } from "vue";
import router from "../../router";
import { ElMessage } from "element-plus";

const user = JSON.parse(localStorage.getItem('canteen-user') || '{}')

const data = reactive({
  tables: []
})

const loadTables = () => {
  request.get('/tables/selectAll').then(res => {
    data.tables = res.data || []
  }).catch((e) => {
    console.log(e);
    if (
      e.response == undefined ||
      e.response.data == undefined
    ) {
      ElMessage.error(e)
    } else {
      ElMessage.error(e.response.data)
    }
  })
}

//订餐，修改餐桌的状态
const addOrder = (item) => {
  item.userId = user.id
  request.put('/tables/addOrder', item).then(res => {
    if (res.code === '200') {
      router.push('/order')
    } else {
      ElMessage.error(res.msg)
    }
  })

}

loadTables()

</script>

<style scoped></style>