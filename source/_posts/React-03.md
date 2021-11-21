---
title: Reactk开发 | 高级概念
date: 2021年11月7日 15:09:49
comments: true
categories: "React开发"
tags: 
    - React
---

欢迎访问：[【React Native】从React开始——高级概念](https://blog.csdn.net/qq_26460841/article/details/121174545)
# 1. 前言
&ensp;&ensp;&ensp;&ensp;在博客[【React Native】从React开始——核心概念](https://blog.csdn.net/qq_26460841/article/details/121046948)中了解了`React`的一些基础用法，并了解到其实和之前学习的微信小程序开发十分相像。感兴趣微信小程序开发的同学可以去同步了解学习下。在[【React Native】从React开始——React Router 基础](https://blog.csdn.net/qq_26460841/article/details/121099969)一篇中学习了在`React`中路由的使用，通过路由，我们的单页面应用看起来也更像是一个完整的前端页面。但是其实当用户点击设定好的链接的时候，因为实际上并不会再向服务器发起页面请求，而是直接根据代码逻辑进行`DOM`的渲染，所以这里加载会更加流畅。

&ensp;&ensp;&ensp;&ensp;当然，仅有前面两篇博客的只是储备其实还是略微不够的，因为在`React`中还有很多高级的用法。在[官网](https://react.docschina.org/docs/getting-started.html#advanced-concepts)中的高级指引部分内容，也就是这篇博客学习的核心。感兴趣可以直接查看官网的文档。

# 2. 内容
## 2.1 Context
> `Context` 提供了一个无需为每层组件手动添加 `props`，就能在组件树间进行数据传递的方法。

在一个典型的 `React` 应用中，数据是通过 `props` 属性自上而下（由父及子）进行传递的，但这种做法对于某些类型的属性而言是极其繁琐的。`Context` 提供了一种在**组件之间共享此类值**的方式，而不必显式地通过组件树的逐层传递 `props`。

【注】：本篇博客的示例代码在[ReactLearn/src_11.04 v0.3/](https://github.com/baiyazi/ReactLearn/tree/main/src_11.04%20v0.3)的基础上进行修改。比如这里修改`NoMatch.js`文件为：

```clike
// NoMatch.js
import './App.css'
import UserInfoContext from './Config'
import React from 'react'

class NoMatch extends React.Component {

  render() {
  	// 使用为this.context
    console.log(this.context)
    return (
      <div>
        <h3>
        {this.context} No match !
        </h3>
      </div>
    )
  }
}

// 设置到当前的context 
NoMatch.contextType = UserInfoContext

export default NoMatch
```
新建一个`Config.js`文件，简单的用来测试：

```clike
// Config.js
import React from "react"

// 直接外部定义一个Context
const UserInfoContext = React.createContext("404")

export default UserInfoContext;
```
最终我们随便输入一个链接，就可以看见效果为：

![在这里插入图片描述](https://img-blog.csdnimg.cn/7cd80a9138f743a6afe50670a111116c.png#pic_center =500x)

这里只是简单的使用，因为在官网中也提到了：
>`Context` 主要应用场景在于很多不同层级的组件需要访问同样一些的数据。请谨慎使用，因为这会使得组件的复用性变差。

所以对于后面的`Consumer`、`displayName`等这里不再介绍。

## 2.2 错误边界
错误边界是一种 `React` **组件**，这种组件可以捕获并打印发生在其子组件树任何位置的 `JavaScript` 错误，并且，它会渲染出备用 `UI`，而不是渲染那些崩溃了的子组件树。错误边界在渲染期间、生命周期方法和整个组件树的构造函数中捕获错误。

> **注意**
错误边界无法捕获以下场景中产生的错误：
1.事件处理
2.异步代码（例如 setTimeout 或 requestAnimationFrame 回调函数）
3.服务端渲染
4.它自身抛出来的错误（并非它的子组件）


如果一个 `class` 组件中定义了 `static getDerivedStateFromError()` 或 `componentDidCatch()` 这两个生命周期方法中的任意一个（或两个）时，那么它就变成一个错误边界。当抛出错误后，用 `static getDerivedStateFromError()` 渲染备用 `UI` ，使用 `componentDidCatch()` 打印错误信息。

`copy`一下`ErrorBoundary`到本地项目的`ErrorBoundary.js`文件中，定义为：

```clike
import React from 'react'

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props)
    this.state = { hasError: false }
  }

  static getDerivedStateFromError(error) {
    // 更新 state 使下一次渲染能够显示降级后的 UI
    return { hasError: true }
  }

  componentDidCatch(error, errorInfo) {
    // 你同样可以将错误日志上报给服务器
    console.log(error, errorInfo)
  }

  render() {
    if (this.state.hasError) {
      // 你可以自定义降级后的 UI 并渲染
      return <h1>Something went wrong.</h1>
    }

    return this.props.children
  }
}

export default ErrorBoundary
```
然后，我们人为的制造一个`bug`在`NoMatch.js`文件中，比如：

```clike
import './App.css'
import UserInfoContext from './Config'
import React from 'react'


class NoMatch extends React.Component {

  render() {
  	// 制造一个bug代码
    console.log(undefined = 1)
    return (
      <div>
        <h3>
        {this.context} No match !
        </h3>
      </div>
    )
  }
}

// 设置到当前的context 
NoMatch.contextType = UserInfoContext

export default NoMatch
```

然后在`index.js`文件中，为默认匹配设置一个错误边界：

```xml
<Route path="*">
    <ErrorBoundary>
        <NoMatch />
    </ErrorBoundary>
</Route>
```

为了看见效果，我们需要将项目打包到发布环境中。因为在开发环境最终会转到一个错误详情页面。比如下面的：
![在这里插入图片描述](https://img-blog.csdnimg.cn/47ba498f52ba4bcda83b3038492a0faf.png#pic_center =700x)
但，这里我们只需要看出现的线上效果。故而我们这里将项目简单打包，使用命令：

```clike
npm run build // 代码会被编译到build文件夹
npm install -g serve // 安装一个服务器
serve -s build // 运行
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/9788915e83d047ac8090a2e089bded7f.png#pic_center =500x)
再次测试，可以看到页面效果为：
![在这里插入图片描述](https://img-blog.csdnimg.cn/a2a9504e8d694d7cbd08017afb307be7.png#pic_center =500x)

### 2.2.1 错误边界应该放置在哪？
错误边界的粒度由你来决定，可以将其包装在最顶层的路由组件并为用户展示一个 “`Something went wrong`” 的错误信息，就像服务端框架经常处理崩溃一样。你也可以将单独的部件包装在错误边界以保护应用其他部分不崩溃。

### 2.2.2 关于 try/catch ？
`try / catch` 很棒但它仅能用于命令式代码：

```clike
try {
  showButton();
} catch (error) {
  // ...
}
```

然而，`React` 组件是声明式的并且具体指出 什么 需要被渲染。错误边界保留了 `React` 的声明性质，其行为符合你的预期。例如，即使一个错误发生在 `componentDidUpdate` 方法中，并且由某一个深层组件树的 `setState` 引起，**其仍然能够冒泡到最近的错误边界**。

## 2.3 Refs 转发
关于转发，[官网-Refs 转发](https://react.docschina.org/docs/forwarding-refs.html)的介绍如为：`Ref` 转发是一项将 `ref` 自动地通过组件传递到其一子组件的技巧。对于大多数应用中的组件来说，这通常不是必需的。但其对某些组件，尤其是**可重用的组件库**是很有用的。也就是说其实学习这块内容是很有必要的，因为在日常开发中，通常都需要定义一些可重用的组件库。

`Ref` 转发是一个可选特性，其允许某些组件接收 `ref`，并将其向下传递（换句话说，“转发”它）给子组件。比如在文档中给出的示例：

```clike
const FancyButton = React.forwardRef((props, ref) => (
  <button ref={ref} className="FancyButton">
    {props.children}
  </button>
));

// 你可以直接获取 DOM button 的 ref：
const ref = React.createRef();
<FancyButton ref={ref}>Click me!</FancyButton>;
```

以下是对上述示例发生情况的逐步解释：

- 我们通过调用 `React.createRef` 创建了一个 `React ref` 并将其赋值给 `ref` 变量。
- 我们通过指定 `ref` 为 `JSX` 属性，将其向下传递给 `<FancyButton ref={ref}>`。
- `React` 传递 `ref` 给 `forwardRef` 内函数 `(props, ref) => ...`，作为其第二个参数。
- 我们向下转发该 `ref` 参数到 `<button ref={ref}>`，将其指定为 `JSX` 属性。
- 当 `ref` 挂载完成，`ref.current` 将指向 `<button> DOM` 节点。

注意：
- 第二个参数 `ref` 只在使用 `React.forwardRef` 定义组件时存在。常规函数和 `class` 组件不接收 `ref` 参数，且 `props` 中也不存在 `ref`。
- `Ref` 转发不仅限于 `DOM` 组件，你也可以转发 `refs` 到 `class` 组件实例中。

比如下面的一个简单案例`/examples/RefsLearn.js`：

```clike
// examples/RefsLearn.js
import React from 'react'

class RefsLearn extends React.Component {
  constructor(props) {
    super(props)
    // 创建React ref对象
    this.myRef = React.createRef()
  }

  handleClick (event){
      // 获取到input的自定义属性值
      let dataId = event.target.getAttribute("data-id");
      let obj = this.myRef.current;
      if(dataId === '1'){
          obj.focus();
      }else if(dataId === '2'){
          obj.disabled = true;
      }else if(dataId === '3'){
          obj.value = "";
      }else{
          // eslint-disable-next-line no-throw-literal
          throw "Error!";
      }
    }

  render() {
    return (
        <div>
        	<!--render中的子控件设置ref，那么就可以用这个ref来得到这个子控件对象-->
            <input type="input" ref={this.myRef}/>
            <ul>
                <li><button onClick={this.handleClick.bind(this)} data-id={1}>获取焦点</button></li>
                <li><button onClick={this.handleClick.bind(this)} data-id={2}>禁用按钮</button></li>
                <li><button onClick={this.handleClick.bind(this)} data-id={3}>清空文本</button></li>
            </ul>
        </div>
    )
  }
}

export default RefsLearn
```

在上面的案例中，可以可以实现预定义的三种效果，截图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/284518aa17c441a384a516adef57bb68.png#pic_center =400x)

通过上面的案例，可以体会到：
> `render`中的子控件设置`ref`，那么就可以用这个`ref`来得到这个子控件对象，这个对象为`ref.current`。当然，操作过程需要一些`JavaScript`的知识，可以参考[w3school网站](https://www.w3school.com.cn/jsref/dom_obj_button.asp)。



## 2.4 Fragments
通常我们在`render`中返回的是一个`div`包起来的一坨控件，比如上面的例子：

```clike
<div>
   <input type="input" ref={this.myRef}/>
    <ul>
        ...
    </ul>
</div>
```

但是有时候，在`render`中使用`div`来包起来的时候，会导致不再是我们想要的`HTML`结构。比如下面的案例：



```clike
class Columns extends React.Component {
  render() {
    return (
      <div>
        <td>Hello</td>
        <td>World</td>
      </div>
    );
  }
}

// 结果：
<table>
  <tr>
  	<!--这里不是我们所希望的结果-->
    <div>
      <td>Hello</td>
      <td>World</td>
    </div>
  </tr>
</table>
```

显然我们不需要`td`外层的`div`元素。所以在`React`中提供了`Fragments`来解决这个问题。可以在`render`的时候使用：

```clike
class Columns extends React.Component {
  render() {
    return (
      <React.Fragment>
        <td>Hello</td>
        <td>World</td>
      </React.Fragment>
    );
  }
}
```
或者使用更加简洁的语法：

```clike
class Columns extends React.Component {
  render() {
    return (
      <>
        <td>Hello</td>
        <td>World</td>
      </>
    );
  }
}
```

来解决这个问题。

## 2.5 高阶组件（HOC）
高阶组件（`HigherOrderComponent`，`HOC`）是 `React` 中用于复用组件逻辑的一种**高级技巧**。`HOC` 自身不是 `React API` 的一部分，它是一种基于 `React` 的组合特性**而形成的设计模式**。

具体而言，**高阶组件是参数为组件，返回值为新组件的函数**。也就是说，高阶组件其实就是一个纯函数，它会接受一个组件作为参数，然后返回一个新的组件。

在官网中给了一个较为复杂的案例来说明这个问题，这里我用一个简单的案例来阐述。比如这里定义`Human`的类，都具有学习的功能，简单定义为：

```clike
// Human.js
import React from "react";

class Human extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            // 用来接收用户通知信息
            message: '随便看看'
        }
    }

    render() {
        return (
             <div>
                 <h1>{ this.state.message }</h1><br/>
             </div>
        );
    }
}


export default Human;
```

比如张三和李四来了，张三喜欢看文学方面的书籍，李四喜欢看计算机相关的书籍。那么对于张三李四，我们就可以这样来写。

```clike
// Human.js
import React from 'react'

class Human extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
        message: this.props.message === undefined ? '随便看看' : this.props.message
    }
  }

  render() {
    return (
      <div>
        <h1>{this.state.message}</h1>
      </div>
    )
  }
}

export const withLearning = function (WrappedComponent, info) {
  return class extends React.Component {
    constructor(props) {
      super(props)
      this.state = {
        // 用来接收用户通知信息
        message: info,
      }
    }

    render() {
      //传递属性message
      return <WrappedComponent message={this.state.message}/>
    }
  }
}

export default Human
```

那么在`index.js`文件中进行定义为：

```clike
<Route path="/Human_v1" component={ Human }></Route>
<Route path="/Human_v2" component={ withLearning(Human, "文学") }></Route>
<Route path="/Human_v3" component={ withLearning(Human, "计算机") }></Route>
```
当我们访问对应的链接的时候，就可以看见对应的不同显示效果。这里不再截图。至于关于HOC的其余注意事项，这里不再复述。

## 2.6 虚拟DOM
`UI` 更新需要昂贵的 `DOM` 操作，而 `React` 内部使用几种巧妙的技术以便最小化 `DOM` 操作次数。为了了解什么是虚拟`DOM`，首先看看传统`Web`加载处理的流程。
- 在传统的 `Web` 应用中，我们往往会把**数据的变化实时地更新到用户界面**中，于是每次数据的微小变动都会引起 `DOM` 树的重新渲染。

- 虚拟`DOM`的目的是将所有操作累加起来，**统计计算出所有的变化后，统一更新一次**`DOM`。

当`Node`节点的更新，虚拟`DOM`会比较两棵`DOM`树的区别，保证最小化的`DOM`操作。比如在[官网-协调](https://react.docschina.org/docs/reconciliation.html)部分提到的`Diffing`算法：
- 当对比两颗树时，`React` 首先比较两棵树的根节点；
- 当根节点为不同类型的元素时，`React` 会拆卸原有的树并且建立起新的树；
- 当比对两个相同类型的 `React` 元素时，`React` 会保留 `DOM` 节点，仅比对及更新有改变的属性；
- 在处理完当前节点之后，`React` 继续对子节点进行递归；
- 在默认条件下，当递归 `DOM` 节点的子元素时，`React` 会**同时遍历**两个子元素的列表；当产生差异时，生成一个 `mutation`。
- 为了解决匹配过程的性能问题，`React` 支持 `key` 属性。当子元素拥有 `key` 时，`React` 使用 `key` 来匹配原有树上的子元素以及最新树上的子元素。这个 `key` 不需要全局唯一，但在列表中需要保持唯一。
- `Key` 应该具有稳定，可预测，以及列表内唯一的特质。不稳定的 `key`（比如通过 `Math.random()` 生成的）会导致许多组件实例和 `DOM` 节点被不必要地重新创建，这可能导致性能下降和子组件中的状态丢失。

重新渲染表示在所有组件内调用 `render` 方法，这不代表 `React` 会卸载或装载它们。`React` 只会基于以上提到的规则来决定如何进行差异的合并。



# 3. 后记
对于React的部分了解的还只是皮毛部分。因为最近还需要写一篇小论文，需要看论文、做实验验证想法，并完成小论文。所以至于在官网提到的静态类型检查、严格模式以及非受控组件等内容，这里就忽略了，之后再补充吧。

___
- [【React Native】从React开始——核心概念](https://blog.csdn.net/qq_26460841/article/details/121046948)
- [【React Native】从React开始——React Router 基础](https://blog.csdn.net/qq_26460841/article/details/121099969)
- [React 高级指引](https://react.docschina.org/docs/getting-started.html#advanced-concepts)
- [React项目的打包](https://www.cnblogs.com/hfxm/p/8462159.html)
- [w3school网站](https://www.w3school.com.cn/jsref/dom_obj_button.asp)
