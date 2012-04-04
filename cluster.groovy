@Grapes([
    @Grab(group = 'org.infinispan', module = 'infinispan-core', version = '5.1.3.FINAL'),
    @Grab(group = 'org.infinispan', module = 'infinispan-tree', version = '5.1.3.FINAL'),
])

import org.infinispan.manager.DefaultCacheManager
import org.infinispan.Cache
import org.infinispan.tree.TreeCacheFactory
import org.infinispan.tree.TreeCache
import org.infinispan.tree.Fqn
import org.infinispan.tree.Node

import javax.transaction.TransactionManager

Cache c = new DefaultCacheManager("infinispan.xml").getCache("MyCache")
TransactionManager tm = c.getAdvancedCache().getTransactionManager()
TreeCache treeCache = new TreeCacheFactory().createTreeCache(c)

Fqn johnFqn = Fqn.fromString('/person/john')
Node<String, Object> john = treeCache.getRoot().addChild(johnFqn)
john.put("firstName", "John")
john.put("lastName", "Smith")
