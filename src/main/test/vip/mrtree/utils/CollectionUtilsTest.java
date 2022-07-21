package vip.mrtree.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CollectionUtilsTest {
    @Test
    public void testJoin_1() {
        List<String> list = Arrays.asList("a", "b", "c");
        String result = CollectionUtils.join(list, ",");
        System.out.println(result);
        Assert.assertEquals("a,b,c", result);
    }

    @Test
    public void testJoin_2() {
        String result = CollectionUtils.join(null, ",");
        System.out.println(result);
        Assert.assertEquals("", result);
    }

    @Test
    public void testJoin_3() {
        String result = CollectionUtils.join(new ArrayList<>(), ",");
        System.out.println(result);
        Assert.assertEquals("", result);
    }

    @Test
    public void testJoin_4() {
        List<String> list = Arrays.asList("a", "b", "c");
        String result = CollectionUtils.join(list, null);
        System.out.println(result);
        Assert.assertEquals("abc", result);
    }

    @Test
    public void testJoin_5() {
        List<String> list = Collections.singletonList("a");
        String result = CollectionUtils.join(list, null);
        System.out.println(result);
        Assert.assertEquals("a", result);
    }
}
