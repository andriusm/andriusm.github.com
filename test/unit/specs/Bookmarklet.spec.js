import Vue from 'vue'
import VueMaterial from 'vue-material'
import Bookmarklet from '@/components/Bookmarklet'

describe('Bookmarklet.vue', () => {
  it('should render correct contents', () => {
    Vue.use(VueMaterial)
    const Constructor = Vue.extend(Bookmarklet)
    const vm = new Constructor().$mount()

    const defaultData = Bookmarklet.data()

    expect(vm.$el.querySelector('.bookmarklet .md-title a').textContent)
      .to.equal(defaultData.bookmarkletName)

    // vm.$el.querySelector('.bookmarklet input').value = 'asdf'

    console.log(vm.$el.querySelector('.bookmarklet input').value)
    console.log(Bookmarklet.data().bookmarkletName)

    expect(vm.$el.querySelector('.bookmarklet textarea').value)
      .to.equal(defaultData.bookmarkletCode)
  })
})
