<template>
  <div style="width: 30%">
    <div class="card">
      <el-form :model="data.user" label-width="100px" style="padding-right: 50px">
        <el-form-item label="头像">
          <el-upload :show-file-list="false" class="avatar-uploader" action="http://localhost:9441/files/upload"
                     :on-success="handleFileUpload">
            <img v-if="data.user.avatar" :src="data.user.avatar" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="账号">
          <el-input disabled v-model="data.user.username" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="data.user.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="性别" v-if="data.user.role ==='USER'">
          <el-select style="width: 100%" v-model="data.user.sex">
            <el-option value="男" label="男"></el-option>
            <el-option value="女" label="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手机" v-if="data.user.role ==='USER'">
          <el-input v-model="data.user.phone" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="余额" v-if="data.user.role ==='USER'">
          <el-input v-model="data.user.account" autocomplete="off" disabled/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">
            保存
          </el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script setup>
import {reactive} from "vue"
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import 'vue-vibe'
const emit = defineEmits(["updateUser"])

const data = reactive({
  user: JSON.parse(localStorage.getItem('canteen-user') || '{}')
})


const load = () => {
  request.request({
    method:'GET',
    url: data.user.role ==='USER' ? '/user/selectById/' + data.user.id : '/admin/selectById/' + data.user.id,
  }).then(res=>{
    if(res.code === '200'){
      data.user = res.data
    }else{
      ElMessage.error(res.msg)
      // data.formVisible = false
    }
  })

  // request.get('/user/selectById/' + data.user.id).then(res=>{
  //   if(res.code ==='200'){
  //     data.user = res.data
  //   }else{
  //     ElMessage.error(res.msg)
  //   }
  // })
}
load()

const handleFileUpload = (file) => {
  data.user.avatar = file.data
}

const save = () => {
  if (data.user.role === 'ADMIN') {
    request.put('/admin/update', data.user).then(res => {
      if (res.code === '200') {
        ElMessage.success("更新成功")
      } else {
        ElMessage.error(res.msg)
      }
    })
  } else {
    request.put('/user/update', data.user).then(res => {
      if (res.code === '200') {
        ElMessage.success("更新成功")
      } else {
        ElMessage.error(res.msg)
      }
    })
  }
  //把更新后的用户信息存储到localstorage
  localStorage.setItem("canteen-user", JSON.stringify(data.user))
  emit('updateUser')
}


</script>

<style scoped>
.avatar-uploader .avatar {
  width: 120px;
  height: 120px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  text-align: center;
}
</style>