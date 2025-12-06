<template>
  <div>

    <div class="card" style="margin-bottom: 10px;" v-if="data.user.role ==='ADMIN'">
      <el-input prefix-icon="Search" style="width: 300px; margin-right: 10px" placeholder="请输入顾客姓名查询" v-model="data.userName"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="warning" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 10px">
      <el-table :data="data.tableData">
        <el-table-column type="selection" width="55" />
        <el-table-column type="index" label="序号" width="70" align="center"/>
        <el-table-column prop="orderNo" label="订单编号" width="300" align="center"/>
        <el-table-column prop="content" label="菜单内容" align="center"/>
        <el-table-column prop="total" label="菜单总价" align="center"/>
        <el-table-column prop="userName" label="用户名称" align="center"/>
        <el-table-column prop="status" label="订单状态" align="center"/>
        <el-table-column label="操作" width="260" fixed="right" align="center" v-if=" data.user.role === 'ADMIN'">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card" v-if="data.total">
      <el-pagination background layout="prev, pager, next" @current-change="load" :page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog v-model="data.formVisible" title="信息" width="40%" destroy-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right: 50px">
        <el-form-item label="订单编号">
          <el-input v-model="data.form.orderNo" autocomplete="off" :disabled="true"/>
        </el-form-item>
        <el-form-item label="订单内容">
          <el-input v-model="data.form.content" autocomplete="off" />
        </el-form-item>
        <el-form-item label="订单总价">
          <el-input v-model="data.form.total" autocomplete="off" />
        </el-form-item>
        <el-form-item label="是否出餐">
          <el-radio-group v-model="data.form.status">
            <el-radio label="是"></el-radio>
            <el-radio label="否"></el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive} from "vue"
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const data = reactive({
  tableData: [],
  total: 0,
  pageNum: 1,  // 当前的页码
  pageSize: 5,  // 每页的个数
  formVisible: false,
  form: {},
  userName: '',
  user: JSON.parse(localStorage.getItem('canteen-user') || '{}')
})

const load = () => {
  request.get('/orders/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      userName: data.userName,
      userId: data.user.role === 'USER' ? data.user.id : null
    }
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data.total
  })
}

load()

const reset = () => {
  data.userName = null
  load()
}


// 保存数据
const save = () => {
  request.put('/orders/update',data.form).then(res => {
    if (res.code === '200') {  //成功
      ElMessage.success('操作成功')
      data.formVisible = false // 关闭弹窗
      load()  // 重新加载表格数据
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

const del = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确认删除吗？', '确认删除', { type: 'warning' }).then(res => {
    request.delete('/orders/delete/' + id).then(res => {
      if (res.code === '200') {  //成功
        ElMessage.success('操作成功')
        load()  // 重新加载表格数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {
    console.log(err)
  })
}

</script>